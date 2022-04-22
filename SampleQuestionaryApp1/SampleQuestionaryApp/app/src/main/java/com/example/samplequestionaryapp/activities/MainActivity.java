package com.example.samplequestionaryapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.example.samplequestionaryapp.R;
import com.example.samplequestionaryapp.model.Questionnaire;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    AppCompatButton btnStartQues, btnShowRes;
    AppCompatImageView backBtn;
    AppCompatTextView toolTxt,toolTxt1;
    int testSubmitted = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testSubmitted = getIntent().getIntExtra("testSubmitted",0);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Questionnaire Demo");
        toolTxt = findViewById(R.id.toolTxt);
        toolTxt1 = findViewById(R.id.toolTxt1);
        backBtn = findViewById(R.id.backBtn);
        toolTxt.setVisibility(View.GONE);
        toolTxt1.setVisibility(View.GONE);
        backBtn.setVisibility(View.GONE);
        setSupportActionBar(toolbar);

        btnShowRes = findViewById(R.id.btnShowRes);
        btnStartQues = findViewById(R.id.btnStartQues);

        if (testSubmitted == 0){
            btnShowRes.setVisibility(View.GONE);
        }else {
            btnShowRes.setVisibility(View.VISIBLE);
        }

        btnStartQues.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
            startActivity(intent);
        });
        btnShowRes.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ShowResultActivity.class);
            startActivity(intent);
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}