package com.gruia.courseplanet.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.gruia.courseplanet.R;

public class CoursesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_courses);
    }

    public void geometryClicked(View v)
    {
        Intent intent = new Intent(this, GeometryActivity.class);
        startActivity(intent);
    }
    public void anatomyClicked(View v)
    {
        Intent intent = new Intent(this, AnatomyActivity.class);
        startActivity(intent);
    }
}