package com.cmcj.gmj.localapp.base.network;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.cmcj.gmj.localapp.APIService;
import com.cmcj.gmj.localapp.application.LocalAppConfig;
import com.cmcj.gmj.localapp.application.LocalApplication;
import com.cmcj.gmj.localapp.utils.LogUtils;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by guomaojian on 16/10/12.
 */

public class RetrofitService {

    private static final String TAG = RetrofitService.class.getSimpleName();
    public static RetrofitService INSTANCE;
    public static Map<String, Retrofit> mRetrofitMap = new HashMap<>();
    public static APIService mAPIService;

    public static final int
            ERROR_CODE = 0,
            HTTP_OK = 200,
            HTTP_PARTIAL = 206,
            HTTP_REDIRECT = 301,
            HTTP_FORBIDDEN = 403,
            HTTP_NOTFOUND = 404,
            HTTP_BADREQUEST = 400,
            HTTP_INTERNALERROR = 500,
            HTTP_NOTIMPLEMENTED = 501;


    private RetrofitService() {
        getRetrfit();
    }

    public static RetrofitService createRetrofit() {
        if (INSTANCE == null) {
            synchronized (LocalApplication.class) {
                if (INSTANCE == null)
                    INSTANCE = new RetrofitService();
            }
        }
        return INSTANCE;
    }

    public Retrofit getRetrfit() {
        return getRetrofit("http://v.juhe.cn/");
    }

    public Retrofit getRetrofit(String host) {
        if (!mRetrofitMap.containsKey(host)) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(host)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(createHttpClient())
                    .build();
            mRetrofitMap.put(host, retrofit);

            if (mAPIService == null)
                mAPIService = retrofit.create(APIService.class);
        }
        return mRetrofitMap.get(host);
    }

    private Interceptor mCacheHeaderInterceptor = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            String cacheHeaderValue = LocalAppConfig.DEBUG ? "public, max-age=2419200" : "public, only-if-cached, max-stale=2419200";
            Request request = originalRequest.newBuilder().build();
            okhttp3.Response response = chain.proceed(request);
            return response.newBuilder().removeHeader("Pragma").removeHeader("Cache-Control").header("Cache-Control", cacheHeaderValue).build();
        }
    };

    private Interceptor mAddParamsInterceptor = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            HttpUrl httpUrl = request.url().newBuilder().addQueryParameter("type", "1234").build();
            request = request.newBuilder().url(httpUrl).build();
            return chain.proceed(request);
        }
    };

    private Interceptor mAddHeaderTypeInterceptor = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                    .addHeader("Accept-Encoding", "gzip, deflate")
                    .addHeader("Connection", "keep-alive")
                    .addHeader("Accept", "*/*")
                    .addHeader("Cookie", "add cookies here")
                    .build();
            return chain.proceed(request);
        }
    };

    @NonNull
    private Interceptor mRequestLogInterceptor() {
        return new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                LogUtils.i(TAG, request.headers().toString());
                LogUtils.i(TAG, "method : " + request.method() + "  url : " + request.url().url());
                okhttp3.Response response = chain.proceed(request);
                return response;
            }
        };
    }

    private OkHttpClient createHttpClient() {
        OkHttpClient.Builder mHttpClientBuilder = new OkHttpClient.Builder();
        mHttpClientBuilder.connectTimeout(LocalAppConfig.CONNET_TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(mCacheHeaderInterceptor)
                .addInterceptor(mAddParamsInterceptor)
                .addNetworkInterceptor(mAddHeaderTypeInterceptor)
                .addNetworkInterceptor(new StethoInterceptor())
                .addNetworkInterceptor(mRequestLogInterceptor());
        return mHttpClientBuilder.build();
    }

    public static APIService getAPIService() {
        return mAPIService;
    }

    public static HashMap<String, List<Call<?>>> mRetrofitCallMap = new HashMap<>();

    public static <T extends LocalResponse<D>, D> void sendLocalRequest(Activity activity, Call<T> call, final LocalResponseListener<D> listener) {
        String key = getCallKey(activity);
        if (!mRetrofitCallMap.containsKey(key)) {
            mRetrofitCallMap.put(key, new ArrayList<Call<?>>());
            mRetrofitCallMap.get(key).add(call);
        } else {
            if (mRetrofitCallMap.get(key).contains(call)) {
                int index = mRetrofitCallMap.get(key).indexOf(call);
                mRetrofitCallMap.get(key).get(index).cancel();
                mRetrofitCallMap.get(key).remove(index);
            }
            mRetrofitCallMap.get(key).add(call);
        }
        sendLocalRequest(call, listener);
    }

    private static <T extends LocalResponse<D>, D> void sendLocalRequest(Call<T> call, final LocalResponseListener<D> listener) {
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (!response.isSuccessful()) {
                    listener.onFailed(-300, "response.isSuccessful = false");
                    return;
                }
                String errorStr = "";
                try {
                    if (response.errorBody() != null) {
                        errorStr = response.errorBody().string();
                        LogUtils.i(TAG, errorStr);
                        if (!TextUtils.isEmpty(errorStr)) {
                            listener.onFailed(-200, "errorBody : " + response.errorBody());
                            return;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (response.code() != HTTP_OK) {
                    listener.onFailed(response.code(), "response.code() != 200");
                    return;
                }

                T localResponse = response.body();
                D data = localResponse.getResult();
                LogUtils.i(TAG, localResponse.getReason());
                if (localResponse.getError_code() == ERROR_CODE && localResponse.getResultcode() == HTTP_OK) {
                    listener.onSuccessed(data);
                } else {
                    listener.onFailed(localResponse.getError_code(), localResponse.getReason());
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                listener.onFailed(-100, t.toString());
            }
        });
    }

    private static String getCallKey(Activity activity) {
        return activity.getPackageName() + activity.getLocalClassName();
    }

    public static void removeCurrentCall(Activity activity) {
        String key = getCallKey(activity);
        if (mRetrofitCallMap.containsKey(key)) {
            for (Call call : mRetrofitCallMap.get(key)) {
                call.cancel();
            }
        }
    }

    public interface LocalResponseListener<D> {

        void onSuccessed(D data);

        void onFailed(int errorCode, String errMsg);
    }

    public static <D> Subscription sendDouBanObservableRequest(Observable observable, final SubscribeRequestProxy<D> subscribeProxy) {
        Subscription subscription = observable
                .flatMap(new Func1<DouBanResponse<D>, Observable<D>>() {
                    @Override
                    public Observable<D> call(DouBanResponse<D> response) {
                        if (response != null && response.getSubjects() != null) {
                            return Observable.just(response.getSubjects());
                        }
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<D>() {
                    @Override
                    public void call(D d) {
                        if (d != null)
                            subscribeProxy.onMainComplete(d);
                        else
                            subscribeProxy.onMainError("Observable解析 or 转换失败");
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if (throwable instanceof SocketTimeoutException)
                            subscribeProxy.onMainError("网络连接超时错误");
                        else
                            subscribeProxy.onMainError("未知错误");
                        LogUtils.i("sendDouBanObservableRequest", throwable.toString());
                    }
                });
        return subscription;
    }

    public interface SubscribeRequestProxy<D> {
        void onMainComplete(D data);

        void onMainError(String errorMsg);
    }
}
