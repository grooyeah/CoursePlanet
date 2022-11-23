package com.gruia.courseplanet.model;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Repository {

    private Application application;
    private FirebaseAuth firebaseAuth;
    private MutableLiveData<FirebaseUser> userMutableLiveData;
    private MutableLiveData<Boolean> loggedOutMutableLiveData;

    public Repository(Application application)
    {
        this.application = application;
        firebaseAuth = FirebaseAuth.getInstance();
        userMutableLiveData = new MutableLiveData<>();
        loggedOutMutableLiveData = new MutableLiveData<>();

//        if(firebaseAuth.getCurrentUser() != null)
//        {
//            userMutableLiveData.postValue(firebaseAuth.getCurrentUser());
//            loggedOutMutableLiveData.postValue(false);
//        }
    }

    public void register(String email, String password)
    {
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(application.getMainExecutor(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    userMutableLiveData.postValue(firebaseAuth.getCurrentUser());
                }else{
                    Toast.makeText(application,"Registration failed: " + task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void login(String email, String password)
    {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(application.getMainExecutor(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    userMutableLiveData.postValue(firebaseAuth.getCurrentUser());
                }else{
                    Toast.makeText(application,"Login failed: " + task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void logout()
    {
        firebaseAuth.signOut();
        loggedOutMutableLiveData.postValue(true);
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public MutableLiveData<Boolean> getLoggedOutMutableLiveData() {
        return loggedOutMutableLiveData;
    }

    public User getLoggedUser()
    {
        User userLogged = new User();
        userLogged.setEmail(firebaseAuth.getCurrentUser().getEmail());
        userLogged.setPassword(firebaseAuth.getCurrentUser().getUid());
        return userLogged;
    }

    public boolean editAccount(User user)
    {

        boolean complete = false;
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        try {
            firebaseUser.updateEmail(user.getEmail()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Log.d(null, "User email updated.");
                        firebaseUser.updatePassword(user.getPassword()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(null, "User password updated.");

                                }
                            }
                        });
                    }

                }

            });
            complete = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return complete;
    }
}
