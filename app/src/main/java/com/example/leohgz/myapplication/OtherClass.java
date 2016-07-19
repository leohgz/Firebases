package com.example.leohgz.myapplication;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by leohgz on 18/7/2016.
 */
public class OtherClass {

    OtherClass(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        if (database!=null)
            Log.d("database!=null","database!=null!!!!!");

        DatabaseReference myRef = database.getReference();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("DATACHANGED","DATAAAAA!!!!!"+dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("onCancelled","onCancelled!!!!!");
            }
        });

    }
}
