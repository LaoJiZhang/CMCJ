package com.cmcj.gmj.localapp.main.presenter;

import android.view.View;

import com.pansijing.common.network.LocalResponse;
import com.pansijing.common.network.RetrofitService;
import com.pansijing.common.base.presenter.BaseActivityDatabindingPresenter;
import com.pansijing.common.base.bean.MovieResponse;
import com.cmcj.gmj.localapp.main.view.IMain;
import com.cmcj.gmj.localapp.main.view.MainActivity;
import com.pansijing.common.utils.LogUtils;
import com.pansijing.common.utils.ToastUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.pansijing.common.network.RetrofitService.getAPIService;

/**
 * Created by guomaojian on 16/9/28.
 */

public class MainPresenter extends BaseActivityDatabindingPresenter<IMain, MainActivity> {

    private static String TAG = MainPresenter.class.getSimpleName();

    public MainPresenter(IMain view) {
        super(view);
    }

    @Override
    protected void finishCreatePresenter() {
        LogUtils.d("finishCreatePresenter");
    }

    public void getStr() {
        LogUtils.d("getStr");
    }

    public void onClickHomeBinding(View view) {
        getView().switchTab(MainActivity.MainTab.MAIN_HOME);
        ToastUtils.showToast("onClickHomeBinding");
    }

    public void onClickNetworkBinding(View view) {
        getView().switchTab(MainActivity.MainTab.MAIN_NEWWORK);
        ToastUtils.showToast("onClickNetworkBinding 123");
    }

    public void onClickDatabaseBinding(View view) {
        getView().switchTab(MainActivity.MainTab.MAIN_DATABASE);
        ToastUtils.showToast("onClickDatabaseBinding");
    }

    public void onClickNewBinding(View view) {
        getView().switchTab(MainActivity.MainTab.MAIN_NEW);
        ToastUtils.showToast("onClickNewBinding");
    }

    public void movieRequest() {
        final Map<String, String> queryMap = new HashMap<>();
        queryMap.put("key", "dc1514f5fb78c2001153f0d280333228");
        queryMap.put("title", "哥斯拉");
        Call<LocalResponse<List<MovieResponse>>> call = getAPIService().loadingMovies(queryMap);
        RetrofitService.sendLocalRequest(getActivity(), call, new RetrofitService.LocalResponseListener<List<MovieResponse>>() {
            @Override
            public void onSuccessed(List<MovieResponse> data) {
                getActivity().finish();
                for (MovieResponse item : data)
                    LogUtils.i("onResponse       " + item.toString());
            }

            @Override
            public void onFailed(int errorCode, String errMsg) {
                LogUtils.i("errorCode = " + errorCode + "    errMsg = " + errMsg);
            }
        });
    }
}
