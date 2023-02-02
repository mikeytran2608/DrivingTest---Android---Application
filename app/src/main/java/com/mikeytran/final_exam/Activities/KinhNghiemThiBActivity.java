package com.mikeytran.final_exam.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ScrollView;

import com.mikeytran.final_exam.R;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;


public class KinhNghiemThiBActivity extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kinhnghiemthib);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        ScrollView scrollView = findViewById(R.id.scrollView);
        OverScrollDecoratorHelper.setUpOverScroll(scrollView);
    }
}
