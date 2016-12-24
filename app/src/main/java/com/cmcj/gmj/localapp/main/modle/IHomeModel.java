package com.cmcj.gmj.localapp.main.modle;

import com.pansijing.common.base.IModel;
import com.pansijing.common.base.bean.MovieEntity;
import com.pansijing.common.network.RetrofitService;

import java.util.List;

/**
 * Created by guomaojian on 16/11/16.
 */

public interface IHomeModel extends IModel {

    void getDouBanTop250(RetrofitService.LocalResponseListener<List<MovieEntity>> listener);

    public static class Factory {
        public static HomeModel create() {
            return new HomeModel();
        }
    }
}
