package com.gruia.courseplanet.database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.gruia.courseplanet.model.User;

public class UserDAO {
    private static UserDAO instance;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref;

    private UserDAO()
    {
        ref = database.getReference("user");
    }

    public static synchronized UserDAO getInstance()
    {
        if(instance == null)
        {
            instance = new UserDAO();
        }

        return instance;
    }

    public void addUser(String userId, User user)
    {
        ref.child("users").child(userId).setValue(user);
    }
}
