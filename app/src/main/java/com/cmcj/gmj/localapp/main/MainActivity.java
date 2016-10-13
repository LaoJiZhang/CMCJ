package com.cmcj.gmj.localapp.main;

import android.databinding.ViewDataBinding;
import android.view.View;
import android.widget.Toast;

import com.cmcj.gmj.localapp.R;
import com.cmcj.gmj.localapp.base.activity.BaseDataBindingActivity;
import com.cmcj.gmj.localapp.base.presenter.BasePresenter;
import com.cmcj.gmj.localapp.databinding.ActivityMainBinding;

public class MainActivity extends BaseDataBindingActivity<MainPresenter> implements IMain {

    private ActivityMainBinding mBinding;

    @Override
    public ActivityProxy createActivityProxy() {
        return new ActivityProxy() {
            @Override
            public int getContentLayoutId() {
                return R.layout.activity_main;
            }

            @Override
            public BasePresenter createActivityPresenter() {
                return new MainPresenter(MainActivity.this);
            }

            @Override
            public void finishCreateDataBinding(ViewDataBinding viewDataBinding) {
                mBinding = (ActivityMainBinding) viewDataBinding;
                mBinding.setPresenter(getPresenter());
                getPresenter().getStr();
                showErrNetworkPage();
            }
        };
    }

    @Override
    public ActivityTitleInfo createTitleInfo() {
        return new ActivityTitleInfo("我是首页标题123", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "aaa", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void refreshRetryBtnClick(View.OnClickListener listener) {
        Toast.makeText(MainActivity.this, "再试一次123", Toast.LENGTH_SHORT).show();
        showNormalPage();
        switchTab(MainActivity.MainTab.MAIN_HOME);
        getPresenter().movieRequest();
    }

    @Override
    public void switchTab(int position) {
        if (mBinding != null)
            mBinding.setSwitchTab(position);
    }

    public interface MainTab {
        public static final int MAIN_HOME = 1;
        public static final int MAIN_NEWWORK = 2;
        public static final int MAIN_DATABASE = 3;
        public static final int MAIN_NEW = 4;
    }
}
