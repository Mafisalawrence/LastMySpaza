package com.example.lastmyspaza.Shared.Classes;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.lastmyspaza.Shared.Interfaces.OnGetDataListener;
import com.example.lastmyspaza.Shared.Models.ManagerDetails;
import com.example.lastmyspaza.Shared.Interfaces.OnGetDataListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


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
        DatabaseReference myRef = firebaseDatabase.getReference("managers");
         return myRef.child(uid).setValue(managerDetails);
    }
    public void getCurrentUserRole(String uid, final OnGetDataListener listener){
        listener.onStart();
        DatabaseReference myRef =  firebaseDatabase.getReference("roles")
                .child(uid).child("roles");
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

    public void getAccountDetailList(String node, final OnGetDataListener listener){
        listener.onStart();
        DatabaseReference myRef = firebaseDatabase.getReference(node);
        myRef.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
                        listener.onSuccess(dataSnapshot);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                        listener.onFailed(databaseError);
                    }
                });
    }
}
