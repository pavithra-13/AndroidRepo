package com.example.devuser4.examresultlist.remote;

import com.example.devuser4.examresultlist.view.ResultModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("exams/11/4879/18/49975/solution")
    Call<ResultModel> getResults();

}
