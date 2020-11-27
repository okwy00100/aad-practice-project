package com.okwy.practiceproject.PagingWithRetrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.okwy.practiceproject.PagingWithRetrofit.Adapter.ItemAdapter;
import com.okwy.practiceproject.PagingWithRetrofit.Model.Answers;
import com.okwy.practiceproject.PagingWithRetrofit.Model.Item;
import com.okwy.practiceproject.PagingWithRetrofit.Retrofit.RetrofitClient;
import com.okwy.practiceproject.PagingWithRetrofit.ViewModel.ItemViewModel;
import com.okwy.practiceproject.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagingActivity extends AppCompatActivity {

    private RecyclerView pageRecycler;
    private ItemViewModel itemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging);

        pageRecycler = findViewById(R.id.pageRecycler);
        pageRecycler.setLayoutManager(new LinearLayoutManager(this));
        pageRecycler.setHasFixedSize(true);


        final ItemAdapter itemAdapter = new ItemAdapter(this);


        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        itemViewModel.getItemPagedList().observe(this, new Observer<PagedList<Item>>() {
            @Override
            public void onChanged(PagedList<Item> items) {
                itemAdapter.submitList(items);
            }
        });

        pageRecycler.setAdapter(itemAdapter);


//        Call<Answers> call = RetrofitClient.getInstance().getRetrofitApi().getAnswers(1, 100, "stackoverflow");
//
//        call.enqueue(new Callback<Answers>() {
//            @Override
//            public void onResponse(Call<Answers> call, Response<Answers> response) {
//                Answers resp = response.body();
//                Toast.makeText(PagingActivity.this, String.valueOf(resp.getItems().size()), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<Answers> call, Throwable t) {
//
//            }
//        });
    }
}