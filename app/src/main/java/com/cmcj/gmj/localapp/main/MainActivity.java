package com.cmcj.gmj.localapp.main;

import android.databinding.ViewDataBinding;
import android.view.View;
import android.widget.Toast;

import com.cmcj.gmj.localapp.R;
import com.cmcj.gmj.localapp.base.BaseDataBindingActivity;
import com.cmcj.gmj.localapp.base.BasePresenter;
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
                getPresenter().getStr();
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
}
