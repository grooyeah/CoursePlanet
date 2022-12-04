package com.gruia.courseplanet.database;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProgressDAO {

    private static ProgressDAO instance;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref;
    private int userProgress;

    private ProgressDAO()
    {
        ref = database.getReference("progress");
        userProgress = 0;
    }

    public static synchronized ProgressDAO getInstance()
    {
        if(instance == null)
        {
            instance = new ProgressDAO();
        }
        return instance;
    }

    public void addProgress(int progress)
    {

        if(progress <= 6)
        {
            progress+=1;
        }
        ref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("user_progress").setValue(progress);
    }

    public void getProgress()
    {


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dateSnapshot: dataSnapshot.getChildren()) {
                    int progress  = dateSnapshot.child("user_progress").getValue(Integer.class);
                    userProgress = progress;
                    addProgress(progress);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void progress()
    {
        getProgress();
    }

    public int getCurrentProgress()
    {
        return userProgress;
    }
}
