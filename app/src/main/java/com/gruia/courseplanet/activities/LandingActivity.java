package com.gruia.courseplanet.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.gruia.courseplanet.R;

public class LandingActivity extends AppCompatActivity {

    public static Activity fa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_landing);
        fa = this;
    }

    public void startButtonClicked(View v)
    {
        Intent intent = new Intent(this, LoginActivity.class);
        LandingActivity.fa.finish();
        startActivity(intent);
    }
}