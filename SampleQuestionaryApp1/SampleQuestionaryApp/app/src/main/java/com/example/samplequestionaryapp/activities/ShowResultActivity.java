package com.example.samplequestionaryapp.activities;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samplequestionaryapp.R;
import com.example.samplequestionaryapp.adapters.QuesReviewAdapter;
import com.example.samplequestionaryapp.model.Questionnaire;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ShowResultActivity extends AppCompatActivity {

    AppCompatTextView toolTxt,toolTxt1;
    AppCompatImageView backBtn;

    Questionnaire questions = null;
    ArrayList<Questionnaire.QuestionsItem> questionsArrayList;

    QuesReviewAdapter quesReviewAdapter;
    RecyclerView quesRecyclerView;
    AppCompatButton btnPrev, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_ques_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Answers");
        toolTxt = findViewById(R.id.toolTxt);
        toolTxt1 = findViewById(R.id.toolTxt1);
        backBtn = findViewById(R.id.backBtn);
        toolTxt.setVisibility(View.GONE);
        toolTxt1.setVisibility(View.GONE);
        backBtn.setVisibility(View.VISIBLE);
        setSupportActionBar(toolbar);

        quesRecyclerView = findViewById(R.id.quesRecyclerView);
        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        btnNext.setVisibility(View.GONE);
        btnPrev.setVisibility(View.GONE);

        backBtn.setOnClickListener(view -> {
            Intent intent = new Intent(ShowResultActivity.this, MainActivity.class);
            intent.putExtra("testSubmitted",0);
            startActivity(intent);
            finish();
        });

        try {
            String jsonFileData = loadJSONFromAsset();

            Gson gson = new Gson();
            Type listUserType = new TypeToken<Questionnaire>() { }.getType();

            questions = gson.fromJson(jsonFileData, listUserType);
            Log.i("data", "> Item que"  + "\n" + questions);

            questionsArrayList = new ArrayList<>();
            for (int i = 0; i < questions.getQuestions().size(); i++) {
                Log.i("data", "> Item questions"  + "\n" + questions.getQuestions().get(i).getQuestion());
                questionsArrayList.add(questions.getQuestions().get(i));
            }

            quesReviewAdapter = new QuesReviewAdapter(ShowResultActivity.this, questionsArrayList, questions.getMarksEachQues());
            quesRecyclerView.setLayoutManager(new LinearLayoutManager(ShowResultActivity.this,
                    LinearLayoutManager.VERTICAL, false));
            quesRecyclerView.setAdapter(quesReviewAdapter);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String loadJSONFromAsset() throws IOException{
        byte[] buffer = new byte[0];
        try {
            AssetManager manager = ShowResultActivity.this.getAssets();
            InputStream is = manager.open("question_file.json");

            int size = is.available();
            buffer = new byte[size];
            is.read(buffer);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new String(buffer, "UTF-8");
    }
}
