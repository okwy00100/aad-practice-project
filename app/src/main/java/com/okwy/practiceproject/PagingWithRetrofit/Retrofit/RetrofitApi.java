package com.okwy.practiceproject.PagingWithRetrofit.Retrofit;

import com.okwy.practiceproject.PagingWithRetrofit.Model.Answers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApi {

    @GET("answers")
    Call<Answers> getAnswers(
            @Query("page") int page,
            @Query("pagesize") int size,
            @Query("site") String site
    );
}
