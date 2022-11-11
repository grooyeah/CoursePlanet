package com.gruia.courseplanet.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.gruia.courseplanet.R;
import com.gruia.courseplanet.viewmodel.LoggedInViewModel;
import com.gruia.courseplanet.viewmodel.LoginRegisterViewModel;

public class MainPageActivity extends AppCompatActivity {

    public Context context;
    private Button logoutButton;
    private LoggedInViewModel viewModel;
    private TextView txtDashboard;
    private MainPageActivity fa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_page);

        fa=this;

        logoutButton = (Button) findViewById(R.id.logoutButton);
        txtDashboard = (TextView) findViewById(R.id.txtDashboard);

        viewModel = new ViewModelProvider(this).get(LoggedInViewModel.class);
        viewModel.getUserMutableLiveData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if(firebaseUser != null)
                {
                    System.out.println(firebaseUser.getEmail());
                    System.out.println(firebaseUser.getEmail());
                    System.out.println(firebaseUser.getEmail());
                    System.out.println(firebaseUser.getEmail());
                    System.out.println(firebaseUser.getEmail());
                    System.out.println(firebaseUser.getEmail());
                    System.out.println(firebaseUser.getEmail());
                    System.out.println(firebaseUser.getEmail());
                    System.out.println(firebaseUser.getEmail());
                    System.out.println(firebaseUser.getEmail());
                    System.out.println(firebaseUser.getEmail());
                    System.out.println(firebaseUser.getEmail());
                    System.out.println(firebaseUser.getEmail());
                    System.out.println(firebaseUser.getEmail());
                    txtDashboard.setText(firebaseUser.getEmail());
                }
            }
        });
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.logout();
            }
        });

        viewModel.getLoggedOutMutableLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean loggedOut) {
                if(loggedOut)
                {
                    logout();
                }
            }
        });

    }

    public void logout()
    {
        startActivity(new Intent(this,LoginActivity.class));
        fa.finish();
    }

    public void coursesPressed(View v)
    {
        Intent intent = new Intent(this, CoursesActivity.class);
        startActivity(intent);
    }

    public void profilePressed(View v)
    {
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
    }

    public void progressPressed(View v)
    {
        Intent intent = new Intent(this, ProgressActivity.class);
        startActivity(intent);
    }

    public void settingsPressed(View v)
    {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}