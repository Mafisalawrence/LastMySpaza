package com.example.lastmyspaza.Shared.ViewModel;


import com.example.lastmyspaza.Shared.Models.Store;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StoreListViewModel extends ViewModel {

    private MutableLiveData<Store> store = new MutableLiveData();

    public void setStore(Store store) {
        this.store.setValue(store);
    }

    public MutableLiveData<Store> getStore() {
        return store;
    }
}
