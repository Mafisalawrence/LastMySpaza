package com.example.lastmyspaza.Shared.Interfaces;

import android.view.View;

import com.example.lastmyspaza.Shared.Models.Store;

public interface OnStoreListLister
{
    void deleteItem(Store store, int i);
    void editItem(Store store);
}
