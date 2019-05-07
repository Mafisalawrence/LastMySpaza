package com.example.lastmyspaza.Shared.Classes;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.lastmyspaza.Shared.Models.ManagerDetails;
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
    public void getCurrrentUserRole(String uid){
        DatabaseReference myRef =  firebaseDatabase.getReference("roles")
                .child(uid).child("role");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
