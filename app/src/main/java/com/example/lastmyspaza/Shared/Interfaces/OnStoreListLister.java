package com.example.lastmyspaza.Shared.Interfaces;

import android.view.View;

import com.example.lastmyspaza.Shared.Models.Store;
import com.google.android.material.card.MaterialCardView;

public interface OnStoreListLister
{
    void cardSelected(Store store, int i, MaterialCardView cardView);
}
