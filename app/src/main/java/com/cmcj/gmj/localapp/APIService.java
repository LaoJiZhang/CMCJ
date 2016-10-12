package com.cmcj.gmj.localapp;

import com.cmcj.gmj.localapp.base.network.LocalResponse;
import com.cmcj.gmj.localapp.main.modle.MovieResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by guomaojian on 16/10/12.
 */

public interface APIService {

    @GET("movie/index")
    Call<LocalResponse<List<MovieResponse>>> loadingMovies(@QueryMap Map<String, String> map);


}
