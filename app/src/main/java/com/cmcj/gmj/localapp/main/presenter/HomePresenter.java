package com.cmcj.gmj.localapp.main.presenter;

import com.cmcj.gmj.localapp.base.presenter.BaseFragmentDatabindingPresenter;
import com.cmcj.gmj.localapp.database.dao.UserDao;
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                UserDao dao = new UserDao();
                dao.insertUser();
            }
        }).start();
    }

    public void getDouBanTop250() {
        mHomeModle.getDouBanTop250(HomePresenter.this);
    }

    public void setContent(String content) {
        if (isAttachView())
            getView().setContent(content);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHomeModle.onDestory();
    }
}
