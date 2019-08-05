package com.example.lastmyspaza.Shared.ViewModel;


import android.content.Entity;

import com.example.lastmyspaza.Shared.Classes.FirebaseQueryLiveData;
import com.example.lastmyspaza.Shared.Models.Store;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class StoreListViewModel extends ViewModel {

    private List<Store> mList = new ArrayList<>();
    private static final DatabaseReference dataRef =  FirebaseDatabase.getInstance().getReference("stores");

    @NonNull
    public LiveData<List<Store>> getStoreList(){
        FirebaseQueryLiveData mLiveData = new FirebaseQueryLiveData(dataRef);
        LiveData<List<Store>> mStoreList = Transformations.map(mLiveData, new Deserializer());
        return mStoreList;
    }

    private class Deserializer implements Function<DataSnapshot, List<Store>> {

        @Override
        public List<Store> apply(DataSnapshot dataSnapshot) {
            mList.clear();
            for(DataSnapshot snap : dataSnapshot.getChildren()){
                Store msg = snap.getValue(Store.class);
                mList.add(msg);
            }
            return mList;
        }
    }

}
