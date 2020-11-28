package com.okwy.practiceproject.ReactiveProgramming.Retrofit;

import com.okwy.practiceproject.ReactiveProgramming.Model.Repo;

import java.util.List;

import retrofit2.http.Path;
import rx.Observable;

import retrofit2.http.GET;

public interface RepoApi {

    @GET("users/{user)/starred")
    Observable<List<Repo>> getStarredRepositories(@Path("user") String userName);
}
