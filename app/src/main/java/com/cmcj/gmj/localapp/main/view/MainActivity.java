package com.cmcj.gmj.localapp.main.view;

import android.databinding.ViewDataBinding;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import com.cmcj.gmj.localapp.R;
import com.cmcj.gmj.localapp.base.activity.BaseDataBindingActivity;
import com.cmcj.gmj.localapp.base.presenter.BasePresenter;
import com.cmcj.gmj.localapp.databinding.ActivityMainBinding;
import com.cmcj.gmj.localapp.main.adapter.MainPagerAdapter;
import com.cmcj.gmj.localapp.main.presenter.MainPresenter;
import com.cmcj.gmj.localapp.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import static android.support.design.widget.TabLayout.MODE_FIXED;

public class MainActivity extends BaseDataBindingActivity<MainPresenter> implements IMain, ViewPager.OnPageChangeListener {

    private List<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = new String[]{"首页", "网络", "数据库", "其他"};
    private ActivityMainBinding mBinding;
    private MainPagerAdapter mMainPagerAdapter;

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

                initData();
                configView();
//                getPresenter().getStr();
//                showErrNetworkPage();
            }
        };
    }

    private void initData() {
        mFragments.add(new HomeFragment());
        mFragments.add(new HomeFragment());
        mFragments.add(new HomeFragment());
        mFragments.add(new HomeFragment());
    }

    private void configView() {
        // 设置显示Toolbar
        setSupportActionBar(mBinding.mainToolbar);

        // 初始化ViewPager的适配器，并设置给它
        mMainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), mTitles, mFragments);
        mBinding.mainViewpager.setAdapter(mMainPagerAdapter);
        // 设置ViewPager最大缓存的页面个数
        mBinding.mainViewpager.setOffscreenPageLimit(mTitles.length);
        // 给ViewPager添加页面动态监听器（为了让Toolbar中的Title可以变化相应的Tab的标题）
        mBinding.mainViewpager.addOnPageChangeListener(this);
        mBinding.mainTablayot.setTabMode(MODE_FIXED);
        // 将TabLayout和ViewPager进行关联，让两者联动起来
        mBinding.mainTablayot.setupWithViewPager(mBinding.mainViewpager);
        // 设置Tablayout的Tab显示ViewPager的适配器中的getPageTitle函数获取到的标题
        mBinding.mainTablayot.setTabsFromPagerAdapter(mMainPagerAdapter);
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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        LogUtils.i(this.getClass().getSimpleName(), "onPageScrolled  position = " + position + "    positionOffset =" + positionOffset + "       positionOffsetPixels =" + positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {
        LogUtils.i(this.getClass().getSimpleName(), "onPageSelected  position = " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        LogUtils.i(this.getClass().getSimpleName(), "onPageScrollStateChanged  state = " + state);
    }

    public interface MainTab {
        public static final int MAIN_HOME = 1;
        public static final int MAIN_NEWWORK = 2;
        public static final int MAIN_DATABASE = 3;
        public static final int MAIN_NEW = 4;
    }
}
