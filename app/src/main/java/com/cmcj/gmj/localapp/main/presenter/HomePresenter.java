package com.cmcj.gmj.localapp.main.presenter;

import com.pansijing.common.network.RetrofitService;
import com.pansijing.common.base.presenter.BaseFragmentDatabindingPresenter;
import com.cmcj.gmj.localapp.main.adapter.HomeNewAdapte;
import com.cmcj.gmj.localapp.main.modle.IHomeModel;
import com.pansijing.common.base.bean.MovieEntity;
import com.cmcj.gmj.localapp.main.view.IHome;
import com.cmcj.gmj.localapp.main.view.MainActivity;

import java.util.List;

/**
 * Created by guomaojian on 16/11/5.
 */

public class HomePresenter extends BaseFragmentDatabindingPresenter<IHome, MainActivity> {

    private static String TAG = HomePresenter.class.getSimpleName();
    private IHomeModel mHomeModle;
    private HomeNewAdapte mHomeNewAdapte;

    public HomeNewAdapte getHomeAdapter() {
        if (mHomeNewAdapte == null) {
            mHomeNewAdapte = new HomeNewAdapte(getView().getRecyclerView());
        }
        return mHomeNewAdapte;
    }


    public HomePresenter(IHome view) {
        super(view);
    }

    @Override
    public void finishCreatedPresenter() {
        mHomeModle = IHomeModel.Factory.create();
        getDouBanTop250();
    }

    public void getDouBanTop250() {
        mHomeModle.getDouBanTop250(new RetrofitService.LocalResponseListener<List<MovieEntity>>() {

            @Override
            public void onSuccessed(List<MovieEntity> data) {
                if (isViewAttach()) {
                    getHomeAdapter().setBindingDatas(data);
                    getView().getMovieSuccess();
                }
            }

            @Override
            public void onFailed(int errorCode, String errMsg) {
                if (isViewAttach())
                    getView().showNetError();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHomeModle.onDestory();
    }
}
