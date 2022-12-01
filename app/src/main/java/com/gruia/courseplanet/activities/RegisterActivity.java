package com.gruia.courseplanet.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.gruia.courseplanet.R;
import com.gruia.courseplanet.viewmodel.LoginRegisterViewModel;

public class RegisterActivity extends AppCompatActivity {

    private LoginRegisterViewModel viewModel;
    private Button registerButton;
    private EditText emailInput,passInput;
    private RegisterActivity fa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        fa = this;

        emailInput =(EditText) findViewById(R.id.registerEmail);
        passInput =(EditText) findViewById(R.id.registerPassword);
        registerButton = (Button) findViewById(R.id.registerButton);

        viewModel = new ViewModelProvider(this).get(LoginRegisterViewModel.class);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString();
                String password = passInput.getText().toString();

                if(email.length() > 0 && password.length() > 0)
                {
                    viewModel.register(email,password);
                    toLogin();

                }
            }
        });
    }

    //Switching back to login

    public void toLogin() {
        startActivity(new Intent(this,LoginActivity.class));
        fa.finish();
    }
}