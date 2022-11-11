package com.gruia.courseplanet.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.gruia.courseplanet.R;
import com.gruia.courseplanet.model.User;

public class SignUpActivity extends AppCompatActivity {

    private SignUpViewModel viewModel;
    private EditText emailInput,passInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);
        emailInput =(EditText) findViewById(R.id.signupEmail);
        passInput =(EditText) findViewById(R.id.signupPassword);
        viewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
    }


    public void signUp(View v)
    {
        String email = (String) emailInput.getText().toString();
        String password =(String) passInput.getText().toString();
        User userSignedUp = viewModel.signUp(email,password);

        if(userSignedUp == null)
        {
            Toast.makeText(SignUpActivity.this, "Registration failed.",
                    Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(SignUpActivity.this, "Registration successful.",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
    }


}