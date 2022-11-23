package com.gruia.courseplanet.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseUser;
import com.gruia.courseplanet.R;
import com.gruia.courseplanet.fragments.profile.AccountFragment;
import com.gruia.courseplanet.fragments.profile.EditAccountFragment;
import com.gruia.courseplanet.model.User;
import com.gruia.courseplanet.viewmodel.LoggedInViewModel;

public class AccountActivity extends AppCompatActivity {

    private LoggedInViewModel viewModel;
    private EditText emailText,passwordText;
    private AccountActivity fa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_account);
        loadFragment(new AccountFragment());
        viewModel = new ViewModelProvider(this).get(LoggedInViewModel.class);
        fa = this;
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction().addToBackStack(null);
        fragmentTransaction.replace(R.id.accountFrameLayout, fragment);
        fragmentTransaction.commit();
    }

    public void changeEditAccountFragment(View v)
    {
        loadFragment(new EditAccountFragment());
    }

    public void logout(View view) {
        viewModel.logout();
    }

    public void editAccount(View view)
    {
        emailText = (EditText) findViewById(R.id.editTextEmailAddress);
        passwordText = (EditText) findViewById(R.id.editTextPassword);
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        confirmEditAccount(email,password);

    }

    public void confirmEditAccount(String email, String password)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Confirm Edit");
        builder.setMessage("Do you wish to change your account details?");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        User userEdited = new User(email,password);
                        boolean complete = viewModel.editAccount(userEdited);
                        if(complete)
                        {
                            changeMainPage();
                        }
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void changeMainPage()
    {
        startActivity(new Intent(this,MainPageActivity.class));
        fa.finish();
    }
}