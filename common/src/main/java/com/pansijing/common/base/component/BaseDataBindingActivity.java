package com.pansijing.common.base.component;

/**
 * Created by guomaojian on 16/9/28.
 */

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.pansijing.common.R;
import com.pansijing.common.base.presenter.BasePresenter;
import com.pansijing.common.databinding.ActivityLayoutBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by guomaojian on 16/9/28.
 */

public abstract class BaseDataBindingActivity<P extends BasePresenter> extends BaseActivity<P> {

    private ActivityProxy mActivityProxy;
    private ActivityTitleInfo mActivityTitleInfo;
    private ActivityFlag mActivityFlag;
    private ActivityLayoutBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mActivityTitleInfo = createTitleInfo();
        mActivityProxy = createActivityProxy();
        mPresenter = (P) mActivityProxy.createActivityPresenter();

        mBinding = DataBindingUtil.inflate(this.getLayoutInflater(), R.layout.activity_layout, null, false);
        mBinding.publicHeader.publicBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mBinding.publicHeader.publicSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mActivityTitleInfo != null)
                    mActivityTitleInfo.clickListener.onClick(v);
            }
        });
        mBinding.publicErrNetwork.refreshRetryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(this);
            }
        });
        mBinding.setInfo(mActivityTitleInfo);
        mBinding.setFlag(createActivityFlag());
        ViewDataBinding contentViewDataBinding = DataBindingUtil.inflate(this.getLayoutInflater(), mActivityProxy.getContentLayoutId(), null, false);
        mBinding.publicContent.addView(contentViewDataBinding.getRoot());
        setContentView(mBinding.getRoot());

        mActivityProxy.finishCreateDataBinding(contentViewDataBinding);
        super.onCreate(savedInstanceState);
    }

    public abstract ActivityProxy createActivityProxy();

    public ActivityTitleInfo createTitleInfo() {
        mActivityTitleInfo = new ActivityTitleInfo("");
        return mActivityTitleInfo;
    }

    public void setActivityTitleInfo(String title) {
        mActivityTitleInfo.setTitle(title);
        mBinding.setInfo(mActivityTitleInfo);
    }

    public ActivityFlag createActivityFlag() {
        mActivityFlag = new ActivityFlag(ActivityFlag.STATE_NORMAL);
        return mActivityFlag;
    }

    public void showLoadingPage() {
        mActivityFlag.flag = ActivityFlag.STATE_LOADING;
        mBinding.setFlag(mActivityFlag);
    }

    public void showNormalPage() {
        mActivityFlag.flag = ActivityFlag.STATE_NORMAL;
        mBinding.setFlag(mActivityFlag);
    }

    public void showErrorPage() {
        mActivityFlag.flag = ActivityFlag.STATE_ERROR;
        mBinding.setFlag(mActivityFlag);
    }

    public void showErrNetworkPage() {
        mActivityFlag.flag = ActivityFlag.STATE_ERR_NETWORK;
        mBinding.setFlag(mActivityFlag);
    }

    public void showEmptyPage() {
        mActivityFlag.flag = ActivityFlag.STATE_EMPTY;
        mBinding.setFlag(mActivityFlag);
    }

    /**
     * @param listenerw
     * @desc 网络不可用 点击重试按钮
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshRetryBtnClick(View.OnClickListener listenerw) {
    }

    /**
     * @desc Activity初始化 Class
     */
    public interface ActivityProxy {

        int getContentLayoutId();

        BasePresenter createActivityPresenter();

        void finishCreateDataBinding(ViewDataBinding viewDataBinding);
    }

    /**
     * @desc 页面标题设置信息 Class
     */
    public static class ActivityTitleInfo {
        public String title;
        public View.OnClickListener clickListener;

        public ActivityTitleInfo(String title) {
            this.title = title;
        }

        public ActivityTitleInfo(String title, View.OnClickListener listener) {
            this.title = title;
            this.clickListener = listener;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    /**
     * @desc 页面显示状态控制类
     */
    public static class ActivityFlag {
        public static final int STATE_LOADING = 0;
        public static final int STATE_NORMAL = 1;
        public static final int STATE_ERROR = 2;
        public static final int STATE_EMPTY = 3;
        public static final int STATE_ERR_NETWORK = 4;

        public int flag;
        public int resId;
        public int strId1;
        public int strId2;

        public ActivityFlag(int flag) {
            this.flag = flag;
        }

    }
}