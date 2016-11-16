package com.cmcj.gmj.localapp.main.modle;

import com.cmcj.gmj.localapp.base.LocalSubscriber;
import com.cmcj.gmj.localapp.base.network.DouBanResponse;
import com.cmcj.gmj.localapp.base.network.RequestParamsFactory;
import com.cmcj.gmj.localapp.base.network.RetrofitService;
import com.cmcj.gmj.localapp.main.presenter.HomePresenter;
import com.cmcj.gmj.localapp.main.view.MainActivity;
import com.cmcj.gmj.localapp.utils.LogUtils;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

import static android.content.ContentValues.TAG;

/**
 * Created by guomaojian on 16/11/16.
 */

public class HomeModel implements IHomeModel {

    @Override
    public void getDouBanTop250(final HomePresenter presenter) {
        Observable<DouBanResponse<List<MovieEntity>>> observable = RetrofitService.getAPIService().loadingDouBanTop250(RequestParamsFactory.getDouBanTop250Params());

        Subscriber<List<MovieEntity>> subscriber = new LocalSubscriber<List<MovieEntity>, MainActivity>(presenter) {
            @Override
            public void onSuccess(List<MovieEntity> data) {
                StringBuilder stringBuilder = new StringBuilder();
                for (MovieEntity item : data) {
                    stringBuilder.append(item.toString());
                    stringBuilder.append("\n\n\n");
                }
                LogUtils.i(TAG, "onNext : " + stringBuilder.toString());
                if (presenter.isAttachView())
                    presenter.getView().setContent(stringBuilder.toString());
            }
        };
        RetrofitService.commonRequest(observable, subscriber);
    }
}
