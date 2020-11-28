package com.okwy.practiceproject.ReactiveProgramming.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.okwy.practiceproject.R;
import com.okwy.practiceproject.ReactiveProgramming.Model.Repo;

import java.util.ArrayList;
import java.util.List;

public class RepoAdapter extends ListAdapter<Repo, RepoAdapter.ViewHolder> {

    public RepoAdapter() {
        super(DIFF_CALLBACK);
    }

    public static final DiffUtil.ItemCallback<Repo> DIFF_CALLBACK = new DiffUtil.ItemCallback<Repo>() {
        @Override
        public boolean areItemsTheSame(@NonNull Repo oldItem, @NonNull Repo newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Repo oldItem, @NonNull Repo newItem) {
            return oldItem.description.equals(newItem.description) && oldItem.htmlUrl.equals(newItem.htmlUrl)
                    && oldItem.language.equals(newItem.language) && oldItem.name.equals(newItem.name)
                    && oldItem.stargazersCount == newItem.stargazersCount;
        }
    };

    //List<Repo> repos = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_github_repo_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Repo currentRepo = getItem(position);
        holder.repoName.setText(currentRepo.name);
        holder.repoDescription.setText(currentRepo.description);
        holder.repoLanguage.setText(currentRepo.language);
        holder.repoStars.setText(currentRepo.stargazersCount);
    }

//    @Override
//    public int getItemCount() {
//        return repos.size();
//    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView repoName, repoDescription, repoLanguage, repoStars;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            repoName = itemView.findViewById(R.id.text_repo_name);
            repoDescription = itemView.findViewById(R.id.text_repo_description);
            repoLanguage = itemView.findViewById(R.id.text_language);
            repoStars = itemView.findViewById(R.id.text_stars);
        }
    }
}
