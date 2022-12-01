package com.gruia.courseplanet.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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
    private static final int MY_CAMERA_REQUEST_CODE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_page);
        fa=this;
        txtDashboard = (TextView) findViewById(R.id.txtDashboard);
        viewModel = new ViewModelProvider(this).get(LoggedInViewModel.class);
        txtDashboard.setText(viewModel.getLoggedUser().getEmail());
    }

    @Override
    protected void onResume() {
        txtDashboard.setText(viewModel.getLoggedUser().getEmail());
        super.onResume();


    }

    public void logout() {
        startActivity(new Intent(this,LoginActivity.class));
        fa.finish();
    }

    //Switching views

    public void coursesPressed(View v) {
        Intent intent = new Intent(this, CoursesActivity.class);
        startActivity(intent);

    }
    public void profilePressed(View v) {
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
    }
    public void progressPressed(View v) {
        Intent intent = new Intent(this, ProgressActivity.class);
        startActivity(intent);
    }
    public void settingsPressed(View v) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}