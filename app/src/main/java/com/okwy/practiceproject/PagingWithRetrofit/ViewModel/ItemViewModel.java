package com.okwy.practiceproject.PagingWithRetrofit.ViewModel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.okwy.practiceproject.PagingWithRetrofit.DataSource.ItemDataSource;
import com.okwy.practiceproject.PagingWithRetrofit.DataSource.ItemDataSourceFactory;
import com.okwy.practiceproject.PagingWithRetrofit.Model.Item;

public class ItemViewModel extends ViewModel {

    private LiveData<PagedList<Item>> itemPagedList;
    private LiveData<PageKeyedDataSource<Integer, Item>> itemLiveDataSource;

    public ItemViewModel() {
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();
        itemLiveDataSource = itemDataSourceFactory.getItemLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ItemDataSource.PAGE_SIZE)
                        .build();

        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, config)).build();
    }

    public LiveData<PagedList<Item>> getItemPagedList() {
        return itemPagedList;
    }

//    public void setItemPagedList(LiveData<PagedList<Item>> itemPagedList) {
//        this.itemPagedList = itemPagedList;
//    }
//
//    public LiveData<PageKeyedDataSource<Integer, Item>> getItemLiveDataSource() {
//        return itemLiveDataSource;
//    }
//
//    public void setItemLiveDataSource(LiveData<PageKeyedDataSource<Integer, Item>> itemLiveDataSource) {
//        this.itemLiveDataSource = itemLiveDataSource;
//    }
}
