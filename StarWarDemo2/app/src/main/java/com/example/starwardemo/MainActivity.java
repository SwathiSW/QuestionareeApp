package com.example.starwardemo.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import com.example.starwardemo.MainActivity2;
import com.example.starwardemo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        startActivity(new Intent(this, MainActivity2.class));

    }
}