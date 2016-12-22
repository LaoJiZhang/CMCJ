package com.cmcj.gmj.localapp.main.presenter;

import android.databinding.ViewDataBinding;

import com.cmcj.gmj.localapp.R;
import com.cmcj.gmj.localapp.base.adapter.DatabindingRecyclerDelegate;
import com.cmcj.gmj.localapp.base.network.RetrofitService;
import com.cmcj.gmj.localapp.base.presenter.BaseFragmentDatabindingPresenter;
import com.cmcj.gmj.localapp.main.adapter.HomeNewAdapte;
import com.cmcj.gmj.localapp.main.modle.IHomeModel;
import com.cmcj.gmj.localapp.main.modle.MovieEntity;
import com.cmcj.gmj.localapp.main.view.IHome;
import com.cmcj.gmj.localapp.main.view.MainActivity;
import com.cmcj.gmj.localapp.utils.LogUtils;

import java.util.List;

/**
 * Created by guomaojian on 16/11/5.
 */

public class HomePresenter extends BaseFragmentDatabindingPresenter<IHome, MainActivity> {

    private static String TAG = HomePresenter.class.getSimpleName();
    private IHomeModel mHomeModle;
    private HomeNewAdapte mHomeNewAdapte = new HomeNewAdapte(getActivity());

    public HomeNewAdapte getHomeAdapter() {
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
                    getHomeAdapter().addHeaderView("我是header Title", new DatabindingRecyclerDelegate<String>() {
                        @Override
                        public int getItemLayoutResId() {
                            return R.layout.view_list_header;
                        }

                        @Override
                        public void onBindItem(ViewDataBinding viewDataBinding, String item, int position) {
                            LogUtils.i("AAA", "onBindItem : " + item.toString());
                        }
                    });
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
