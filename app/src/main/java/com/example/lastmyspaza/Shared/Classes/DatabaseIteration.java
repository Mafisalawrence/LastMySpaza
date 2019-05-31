package com.example.lastmyspaza.Shared.Classes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.lastmyspaza.Shared.Fragments.Registration.StoreDetails;
import com.example.lastmyspaza.Shared.Interfaces.OnGetDataListener;
import com.example.lastmyspaza.Shared.Models.ManagerDetails;
import com.example.lastmyspaza.Shared.Interfaces.OnGetDataListener;
import com.example.lastmyspaza.Shared.Models.Product;
import com.example.lastmyspaza.Shared.Models.Store;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class DatabaseIteration {

    private Context context;
    private FirebaseDatabase firebaseDatabase;

    public DatabaseIteration(Context _context){
        context = _context;
        firebaseDatabase = FirebaseDatabase.getInstance();
    }
    public Task<Void> addRoleToDB(String role, String uid){
        DatabaseReference myRef = firebaseDatabase.getReference("roles");
       return myRef.child(uid).child("roles").setValue(role);
    }

    public Task<Void> addManagerInformationToDb(String uid, ManagerDetails managerDetails)
    {
        DatabaseReference myRef = firebaseDatabase.getReference("users");
         return myRef.child(uid).setValue(managerDetails);
    }
    public void addStoresDetailsToDb(ArrayList<Store> stores){
        DatabaseReference myRef = firebaseDatabase.getReference("stores");
        for(Store store:stores)
        {
            myRef.child(myRef.push().getKey()).setValue(store);
        }
    }
    public void addStoresDetailsToDb(Store store){
        DatabaseReference myRef = firebaseDatabase.getReference("stores");
        myRef.child(myRef.push().getKey()).setValue(store);

    }
    public void addManagerToStore(String uid,Store store){
        DatabaseReference myRef = firebaseDatabase.getReference("stores");
        myRef.child(store.getStoreId()+"/storeManger").setValue(store.getStoreManager());
    }

    public Task<Void> addProductDetailsToDb(String storeID, Product productDetails)
    {
        //TODO GET NUMBER OF PRODUCTS
        DatabaseReference myRef = firebaseDatabase.getReference("products");
        return myRef.child(storeID).child(myRef.push().getKey()).setValue(productDetails);
    }
    public Task<Void> deleteProductFromDb(String uid,Product product)
    {
        DatabaseReference myRef = firebaseDatabase.getReference("products");
        return myRef.child(uid).child(myRef.push().getKey()).removeValue();
    }

    public void getCurrentUserRole(String uid, final OnGetDataListener listener){
        listener.onStart();
        DatabaseReference myRef =  firebaseDatabase.getReference("users")
                .child(uid).child("role");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listener.onSuccess(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onFailed(databaseError);
            }
        });
    }
    public void getManagerStore(String uid, final OnGetDataListener listener){
        DatabaseReference mRef = firebaseDatabase.getReference("stores");
        mRef.orderByChild("storeManger").equalTo(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listener.onSuccess(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onFailed(databaseError);
            }
        });
    }

    public void getAllStoresOfOwner(String node, String uid, final OnGetDataListener listener){
        listener.onStart();
        DatabaseReference myRef = firebaseDatabase.getReference(node);
        myRef.orderByChild("storeOwner").equalTo(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listener.onSuccess(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onFailed(databaseError);
            }
        });

    }
    public void getAllStores(String node, final OnGetDataListener listener){
        listener.onStart();
        DatabaseReference myRef = firebaseDatabase.getReference(node);
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listener.onSuccess(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onFailed(databaseError);
            }
        });

    }
    public void getAccountDetailList(String node, final OnGetDataListener listener){
        listener.onStart();
        DatabaseReference myRef = firebaseDatabase.getReference(node);
        myRef.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        listener.onSuccess(dataSnapshot);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                        listener.onFailed(databaseError);
                    }
                });
    }
    public void getAllProducts(String node, String storeId, final OnGetDataListener listener){
        listener.onStart();
        DatabaseReference myRef = firebaseDatabase.getReference(node).child(storeId);
                myRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        listener.onSuccess(dataSnapshot);
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}
