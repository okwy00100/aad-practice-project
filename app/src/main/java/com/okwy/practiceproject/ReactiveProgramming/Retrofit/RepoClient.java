package com.okwy.practiceproject.ReactiveProgramming.Retrofit;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepoClient {

    private static final String BASE_URL = "https://api.github.com/";
    private static RepoClient instance;
    private final Retrofit retrofit;

    public RepoClient() {
        final Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
               // .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    public static synchronized RepoClient getInstance() {
        if (instance == null) {
            instance = new RepoClient();
        }
        return instance;
    }

    public RepoApi getRepoApi() {
        return retrofit.create(RepoApi.class);
    }
}
