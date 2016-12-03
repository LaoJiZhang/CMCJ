package com.cmcj.gmj.localapp.main.modle;

import com.cmcj.gmj.localapp.base.network.RequestParamsFactory;
import com.cmcj.gmj.localapp.base.network.RetrofitService;
import com.cmcj.gmj.localapp.utils.LogUtils;

import java.util.List;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by guomaojian on 16/11/16.
 */

public class HomeModel implements IHomeModel {
    private static final String TAG = HomeModel.class.getSimpleName();
    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    @Override
    public void getDouBanTop250(final RetrofitService.LocalResponseListener<List<MovieEntity>> listener) {
        Subscription subscription = RetrofitService.sendDouBanObservableRequest(RetrofitService.getAPIService().loadingDouBanTop250(RequestParamsFactory.getDouBanTop250Params()),
                new RetrofitService.SubscribeRequestProxy<List<MovieEntity>>() {

                    @Override
                    public void onMainComplete(List<MovieEntity> data) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (MovieEntity item : data) {
                            stringBuilder.append(item.toString());
                            stringBuilder.append("\n\n\n");
                        }
                        LogUtils.i(TAG, "onMainComplete : " + stringBuilder.toString());
                        listener.onSuccessed(data);
                    }

                    @Override
                    public void onMainError(String errorMsg) {
                        LogUtils.i(TAG, errorMsg);
                        listener.onFailed(-1, errorMsg);
                    }
                });

        mCompositeSubscription.add(subscription);
    }

    @Override
    public void onDestory() {
        if (!mCompositeSubscription.isUnsubscribed())
            mCompositeSubscription.unsubscribe();
    }
}
