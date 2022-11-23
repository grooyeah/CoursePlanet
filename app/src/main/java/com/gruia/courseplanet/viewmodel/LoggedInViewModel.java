package com.gruia.courseplanet.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;
import com.gruia.courseplanet.model.Repository;
import com.gruia.courseplanet.model.User;

public class LoggedInViewModel extends AndroidViewModel {

    private Repository repository;
    private MutableLiveData<FirebaseUser> userMutableLiveData;
    private MutableLiveData<Boolean> loggedOutMutableLiveData;


    public LoggedInViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);
        userMutableLiveData = repository.getUserMutableLiveData();
        loggedOutMutableLiveData = repository.getLoggedOutMutableLiveData();
    }

    public void logout()
    {
        repository.logout();
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public MutableLiveData<Boolean> getLoggedOutMutableLiveData() {
        return loggedOutMutableLiveData;
    }

    public User getLoggedUser()
    {
        return repository.getLoggedUser();
    }

    public boolean editAccount(User user)
    {
        boolean complete = repository.editAccount(user);
        return complete;
    }
}
