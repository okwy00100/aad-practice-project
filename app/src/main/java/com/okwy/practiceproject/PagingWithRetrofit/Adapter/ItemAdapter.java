package com.okwy.practiceproject.PagingWithRetrofit.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.okwy.practiceproject.PagingWithRetrofit.Model.Item;
import com.okwy.practiceproject.R;

public class ItemAdapter extends PagedListAdapter<Item, ItemAdapter.ViewHolder> {

    private Context context;


    public ItemAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    public static final DiffUtil.ItemCallback<Item> DIFF_CALLBACK = new DiffUtil.ItemCallback<Item>() {
        @Override
        public boolean areItemsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.getAnswer_id() == newItem.getAnswer_id();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.getOwner().getProfile_image().equals(newItem.getOwner().getProfile_image())
                    && oldItem.getOwner().getDisplay_name().equals(newItem.getOwner().getDisplay_name());
        }
    };


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.paging_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item currentItem = getItem(position);
        if(currentItem != null){
            holder.textview.setText(currentItem.getOwner().getDisplay_name());
            Glide.with(context).load(currentItem.getOwner().getProfile_image()).into(holder.imageview);
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageview;
        TextView textview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageview = itemView.findViewById(R.id.image_view);
            textview = itemView.findViewById(R.id.text_view_creator);
        }
    }
}
