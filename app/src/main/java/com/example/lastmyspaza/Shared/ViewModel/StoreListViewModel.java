package com.example.lastmyspaza.Shared.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.lastmyspaza.Shared.Models.Store;

public class StoreListViewModel extends ViewModel {

    private MutableLiveData<Store> store = new MutableLiveData();

    public void setStore(Store store) {
        this.store.setValue(store);
    }

    public MutableLiveData<Store> getStore() {
        return store;
    }
}
