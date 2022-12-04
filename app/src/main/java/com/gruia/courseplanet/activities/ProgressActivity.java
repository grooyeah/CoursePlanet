package com.gruia.courseplanet.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.gruia.courseplanet.R;
import com.gruia.courseplanet.database.ProgressDAO;

public class ProgressActivity extends AppCompatActivity {

    private ProgressDAO progressDAO = ProgressDAO.getInstance();
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_progress);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(6);
        progressBar.setProgress(progressDAO.getCurrentProgress());
    }
}