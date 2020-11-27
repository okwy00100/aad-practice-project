package com.okwy.practiceproject.PagingWithRetrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.okwy.practiceproject.PagingWithRetrofit.Model.Answers;
import com.okwy.practiceproject.PagingWithRetrofit.Retrofit.RetrofitClient;
import com.okwy.practiceproject.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagingActivity extends AppCompatActivity {

    private RecyclerView pageRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging);

        pageRecycler = findViewById(R.id.pageRecycler);
        pageRecycler.setHasFixedSize(true);
        pageRecycler.setLayoutManager(new LinearLayoutManager(this));

        Call<Answers> call = RetrofitClient.getInstance().getRetrofitApi().getAnswers(1, 100, "stackoverflow");

        call.enqueue(new Callback<Answers>() {
            @Override
            public void onResponse(Call<Answers> call, Response<Answers> response) {
                Answers resp = response.body();
                Toast.makeText(PagingActivity.this, String.valueOf(resp.getItems().size()), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Answers> call, Throwable t) {

            }
        });
    }
}