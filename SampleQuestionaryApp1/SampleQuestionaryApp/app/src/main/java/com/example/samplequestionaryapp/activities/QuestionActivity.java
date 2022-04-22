package com.example.samplequestionaryapp.activities;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samplequestionaryapp.R;
import com.example.samplequestionaryapp.adapters.QuestionsAdapter;
import com.example.samplequestionaryapp.model.Questionnaire;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {

    RecyclerView quesRecyclerView;
    AppCompatButton btnPrev, btnNext;
    AppCompatImageView backBtn;
    AppCompatTextView toolTxt, toolTxt1;

    Questionnaire questions = null;
    ArrayList<Questionnaire.QuestionsItem> questionsArrayList;
    QuestionsAdapter questionsAdapter;

    int quesPos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_ques_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Questions");
        toolTxt = findViewById(R.id.toolTxt);
        toolTxt1 = findViewById(R.id.toolTxt1);
        backBtn = findViewById(R.id.backBtn);
        toolTxt.setVisibility(View.VISIBLE);
        toolTxt1.setVisibility(View.VISIBLE);
        backBtn.setVisibility(View.VISIBLE);
        setSupportActionBar(toolbar);

        quesRecyclerView = findViewById(R.id.quesRecyclerView);
        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);

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
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (quesPos == questionsArrayList.size()){
            btnNext.setText(getResources().getText(R.string.submit));
        }else if (quesPos == 0){
            btnPrev.setVisibility(View.GONE);
        }

        btnNext.setOnClickListener(view -> nextBtnClick());
        btnPrev.setOnClickListener(view -> prevBtnClick());
        backBtn.setOnClickListener(view -> {
            Intent intent = new Intent(QuestionActivity.this, MainActivity.class);
            intent.putExtra("testSubmitted",0);
            startActivity(intent);
            finish();
        });

        questionsAdapter = new QuestionsAdapter(QuestionActivity.this,questionsArrayList);
        quesRecyclerView.setLayoutManager(new LinearLayoutManager(QuestionActivity.this,
                LinearLayoutManager.HORIZONTAL,true));
        quesRecyclerView.setAdapter(questionsAdapter);

        quesRecyclerView.scrollToPosition(quesPos);

        toolTxt1.setText("/"+questionsArrayList.size());
        toolTxt.setText("" + (quesPos+1));

    }

    public void nextBtnClick() {
        quesPos++;
        quesPosChange();
        if (quesPos == questionsArrayList.size()) {
            Intent intent = new Intent(QuestionActivity.this, MainActivity.class);
            intent.putExtra("testSubmitted",1);
            startActivity(intent);
            finish();
            Toast.makeText(getApplicationContext(), "Test Submitted Successfully", Toast.LENGTH_SHORT).show();
        }else {
            toolTxt.setText("" + (quesPos+1));
            questionsAdapter = new QuestionsAdapter(QuestionActivity.this, questionsArrayList);
            quesRecyclerView.setLayoutManager(new LinearLayoutManager(QuestionActivity.this,
                    LinearLayoutManager.HORIZONTAL, true));
            quesRecyclerView.setAdapter(questionsAdapter);

            quesRecyclerView.scrollToPosition(quesPos);
        }
    }

    private void quesPosChange(){
        if (quesPos == (questionsArrayList.size()-1)){
            btnNext.setText(getResources().getText(R.string.submit));
            btnPrev.setVisibility(View.VISIBLE);
        }else if (quesPos == 0){
            btnPrev.setVisibility(View.GONE);
            btnNext.setText(getResources().getText(R.string.next));
        }else {
            btnPrev.setVisibility(View.VISIBLE);
            btnNext.setVisibility(View.VISIBLE);
            btnNext.setText(getResources().getText(R.string.next));
        }
    }

    public void prevBtnClick() {
        quesPos--;
        toolTxt.setText("" + quesPos);
        quesPosChange();
        questionsAdapter = new QuestionsAdapter(QuestionActivity.this,questionsArrayList);
        quesRecyclerView.setLayoutManager(new LinearLayoutManager(QuestionActivity.this,
                LinearLayoutManager.HORIZONTAL,true));
        quesRecyclerView.setAdapter(questionsAdapter);

        quesRecyclerView.scrollToPosition(quesPos);
    }

    public String loadJSONFromAsset() throws IOException{
        byte[] buffer = new byte[0];
        try {
            AssetManager manager = QuestionActivity.this.getAssets();
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
