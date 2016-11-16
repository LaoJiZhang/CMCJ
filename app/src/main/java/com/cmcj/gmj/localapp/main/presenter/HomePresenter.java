package com.cmcj.gmj.localapp.main.presenter;

import com.cmcj.gmj.localapp.base.presenter.BaseFragmentDatabindingPresenter;
import com.cmcj.gmj.localapp.main.modle.IHomeModel;
import com.cmcj.gmj.localapp.main.view.IHome;
import com.cmcj.gmj.localapp.main.view.MainActivity;
import com.cmcj.gmj.localapp.utils.LogUtils;

/**
 * Created by guomaojian on 16/11/5.
 */

public class HomePresenter extends BaseFragmentDatabindingPresenter<IHome, MainActivity> {

    private static String TAG = HomePresenter.class.getSimpleName();
    private IHomeModel mHomeModle;

    public HomePresenter(IHome view) {
        super(view);
    }

    @Override
    public void finishCreatedPresenter() {
        LogUtils.i("finishCreatedPresenter");
        mHomeModle = IHomeModel.Factory.create();
    }

    public void getDouBanTop250() {
//        Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
        mHomeModle.getDouBanTop250(HomePresenter.this);
//            }
//        }).subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                LogUtils.i("AAA");
//            }
//        });
    }
}
