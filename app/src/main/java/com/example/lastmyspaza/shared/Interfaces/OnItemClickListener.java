package com.example.lastmyspaza.shared.Interfaces;

import com.example.lastmyspaza.shared.Models.Product;
import com.example.lastmyspaza.shared.Models.Store;

public interface OnItemClickListener
{
    void onItemClick(Product product);
    void onStoreItemClick (Store store);
}

