package com.gruia.courseplanet.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseUser;
import com.gruia.courseplanet.R;
import com.gruia.courseplanet.viewmodel.LoginRegisterViewModel;

public class LoginActivity extends AppCompatActivity {

    private LoginRegisterViewModel viewModel;
    private EditText emailInput,passInput;
    private Button loginButton,registerButton;
    public Activity fa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        emailInput =(EditText) findViewById(R.id.loginEmail);
        passInput =(EditText) findViewById(R.id.loginPassword);
        loginButton = (Button) findViewById(R.id.loginButton);
        registerButton = (Button) findViewById(R.id.registerButton);


        fa = this;

        viewModel = new ViewModelProvider(this).get(LoginRegisterViewModel.class);
        viewModel.getUserMutableLiveData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if(firebaseUser != null)
                {
                    switchToMainPage();
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString();
                String password = passInput.getText().toString();

                if(email.length() > 0 && password.length() > 0)
                {
                    viewModel.register(email,password);
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString();
                String password = passInput.getText().toString();

                if(email.length() > 0 && password.length() > 0)
                {
                    viewModel.login(email,password);
                }
            }
        });
    }

    public void switchToMainPage()
    {
        startActivity(new Intent(this,MainPageActivity.class));
        fa.finish();
    }



}