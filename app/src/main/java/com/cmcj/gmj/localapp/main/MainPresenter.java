package com.cmcj.gmj.localapp.main;

import android.view.View;

import com.cmcj.gmj.localapp.base.network.LocalResponse;
import com.cmcj.gmj.localapp.base.network.RetrofitService;
import com.cmcj.gmj.localapp.base.presenter.BaseDatabindingPresenter;
import com.cmcj.gmj.localapp.base.view.IBaseView;
import com.cmcj.gmj.localapp.main.modle.MovieResponse;
import com.cmcj.gmj.localapp.utils.LogUtils;
import com.cmcj.gmj.localapp.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * Created by guomaojian on 16/9/28.
 */

public class MainPresenter extends BaseDatabindingPresenter<IMain, MainActivity> {

    private List<Call> mCalls = new ArrayList<>();

    public MainPresenter(IBaseView view) {
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
        Call<LocalResponse<List<MovieResponse>>> movieCall = RetrofitService.getAPIService().loadingMovies(queryMap);
        mCalls.add(movieCall);
        RetrofitService.sendLocalRequest(getActivity(), movieCall, new RetrofitService.LocalResponseListener<List<MovieResponse>>() {
            @Override
            public void onSuccessed(List<MovieResponse> data) {
                getActivity().finish();
                LogUtils.i("aaa");
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
