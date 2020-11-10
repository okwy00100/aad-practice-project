package com.okwy.practiceproject.Main.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.okwy.practiceproject.R;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context context;
    private List<String> conceptList;
    private ListItemClickListener listItemClickListener;

    public MainAdapter(Context context, List<String> conceptList, ListItemClickListener listItemClickListener) {
        this.context = context;
        this.conceptList = conceptList;
        this.listItemClickListener = listItemClickListener;
    }

    public interface ListItemClickListener{
        void onListItemClick(String menuItem, int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.main_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(conceptList.get(position), this.listItemClickListener);
    }

    @Override
    public int getItemCount() {
        return conceptList != null ? conceptList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView menuItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            menuItem = itemView.findViewById(R.id.menuItem);
        }

        void bind(final String item, final ListItemClickListener listItemClickListener){

            menuItem.setText(item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listItemClickListener.onListItemClick(item, ViewHolder.this.getAdapterPosition());
                }
            });
        }


    }
}
