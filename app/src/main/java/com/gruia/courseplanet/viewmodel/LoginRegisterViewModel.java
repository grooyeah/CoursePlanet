package com.gruia.courseplanet.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;
import com.gruia.courseplanet.model.Repository;

public class LoginRegisterViewModel extends AndroidViewModel {

    private Repository repository;
    private MutableLiveData<FirebaseUser> userMutableLiveData;


    public LoginRegisterViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);
        userMutableLiveData = repository.getUserMutableLiveData();
    }

    public void register(String email, String password)
    {
        repository.register(email,password);
    }

    public void login(String email,String password)
    {
        repository.login(email,password);
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }
}
