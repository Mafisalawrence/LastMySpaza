package com.example.lastmyspaza.shared.viewModel;


import com.example.lastmyspaza.shared.Models.Store;

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
