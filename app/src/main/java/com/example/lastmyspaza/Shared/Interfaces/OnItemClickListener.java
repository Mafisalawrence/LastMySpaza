package com.example.lastmyspaza.Shared.Interfaces;

import android.view.View;

import com.example.lastmyspaza.Shared.Models.Product;
import com.example.lastmyspaza.Shared.Models.Store;

public interface OnItemClickListener
{
    void onItemClick(Product product);
    void onStoreItemClick (Store store);
}

