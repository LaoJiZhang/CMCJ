package com.cmcj.gmj.localapp.main.presenter;

import android.content.Intent;

import com.cmcj.gmj.localapp.base.adapter.RecyclerItemClickListener;
import com.cmcj.gmj.localapp.base.network.RetrofitService;
import com.cmcj.gmj.localapp.base.presenter.BaseFragmentDatabindingPresenter;
import com.cmcj.gmj.localapp.detailweb.view.CommonWebActivity;
import com.cmcj.gmj.localapp.main.adapter.HomeAdapter;
import com.cmcj.gmj.localapp.main.modle.IHomeModel;
import com.cmcj.gmj.localapp.main.modle.MovieEntity;
import com.cmcj.gmj.localapp.main.view.IHome;
import com.cmcj.gmj.localapp.main.view.MainActivity;

import java.util.List;

/**
 * Created by guomaojian on 16/11/5.
 */

public class HomePresenter extends BaseFragmentDatabindingPresenter<IHome, MainActivity> {

    private static String TAG = HomePresenter.class.getSimpleName();
    private IHomeModel mHomeModle;
    private HomeAdapter mHomeAdapter = new HomeAdapter(getActivity(), new RecyclerItemClickListener<MovieEntity>() {
        @Override
        public void onItemClickCall(MovieEntity entity) {
            Intent intent = new Intent(getActivity(), CommonWebActivity.class);
            intent.putExtra(CommonWebActivity.WEB_URL, entity.getAlt());
            getActivity().startActivity(intent);
        }
    });

    public HomeAdapter getHomeAdapter() {
        return mHomeAdapter;
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
