package com.okwy.practiceproject.ReactiveProgramming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.okwy.practiceproject.R;
import com.okwy.practiceproject.ReactiveProgramming.Adapter.RepoAdapter;
import com.okwy.practiceproject.ReactiveProgramming.Model.Repo;
import com.okwy.practiceproject.ReactiveProgramming.Retrofit.RepoClient;

import java.util.List;

//import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity {


    private static final String TAG = RxJavaActivity.class.getSimpleName();

    private RecyclerView rxRecycler;
    private EditText githubInput;
    private Button search;
    private Subscription subscription;
    private RepoAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);

        rxRecycler = findViewById(R.id.rxRecycler);
        githubInput = findViewById(R.id.github_input);
        search = findViewById(R.id.search);

        rxRecycler.setLayoutManager(new LinearLayoutManager(this));
        rxRecycler.setHasFixedSize(true);
        adapter = new RepoAdapter();
        rxRecycler.setAdapter(adapter);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = githubInput.getText().toString().trim();
                if (!TextUtils.isEmpty(username)) {
                    getStarredRepos(username);
                } else {
                    Toast.makeText(RxJavaActivity.this, "Please input a username", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void getStarredRepos(String username) {
        subscription = RepoClient.getInstance()
                .getRepoApi()
                .getStarredRepositories(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Repo>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "In onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "In onError()");
                    }

                    @Override
                    public void onNext(List<Repo> repos) {
                        adapter.submitList(repos);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }
}