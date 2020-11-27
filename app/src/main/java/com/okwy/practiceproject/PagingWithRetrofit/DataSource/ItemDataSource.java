package com.okwy.practiceproject.PagingWithRetrofit.DataSource;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.okwy.practiceproject.PagingWithRetrofit.Model.Answers;
import com.okwy.practiceproject.PagingWithRetrofit.Model.Item;
import com.okwy.practiceproject.PagingWithRetrofit.Retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDataSource extends PageKeyedDataSource<Integer, Item> {

    public static final int PAGE_SIZE = 50;
    private static final int PAGE_INITIAL = 1;
    private static final String PAGE_SITE = "stackoverflow";


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Item> callback) {
        RetrofitClient.getInstance()
                .getRetrofitApi()
                .getAnswers(PAGE_INITIAL, PAGE_SIZE, PAGE_SITE)
                .enqueue(new Callback<Answers>() {
                    @Override
                    public void onResponse(Call<Answers> call, Response<Answers> response) {
                        if (response.body() != null) {
                            callback.onResult(response.body().items, null, PAGE_INITIAL+1);
                        }
                    }

                    @Override
                    public void onFailure(Call<Answers> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Item> callback) {
        RetrofitClient.getInstance()
                .getRetrofitApi()
                .getAnswers(params.key, PAGE_SIZE, PAGE_SITE)
                .enqueue(new Callback<Answers>() {
                    @Override
                    public void onResponse(Call<Answers> call, Response<Answers> response) {
                        if(response.body() != null){
                            Integer adjacentPageKey = (params.key > 1) ? params.key - 1 : null;
                            callback.onResult(response.body().items, adjacentPageKey);
                        }
                    }

                    @Override
                    public void onFailure(Call<Answers> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Item> callback) {
        RetrofitClient.getInstance()
                .getRetrofitApi()
                .getAnswers(params.key, PAGE_SIZE, PAGE_SITE)
                .enqueue(new Callback<Answers>() {
                    @Override
                    public void onResponse(Call<Answers> call, Response<Answers> response) {
                        if(response.body() != null){
                            Integer adjacentPageKey = response.body().has_more ? params.key + 1 : null;
                            callback.onResult(response.body().items, adjacentPageKey);
                        }
                    }

                    @Override
                    public void onFailure(Call<Answers> call, Throwable t) {

                    }
                });
    }





}
