package com.example.samplequestionaryapp.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.samplequestionaryapp.R;
import com.example.samplequestionaryapp.model.Questionnaire;

import java.util.ArrayList;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder> {

    Activity context;
    ArrayList<Questionnaire.QuestionsItem> quesArrayList;

    public QuestionsAdapter(Activity context, ArrayList<Questionnaire.QuestionsItem> userArrayList) {
        this.context = context;
        this.quesArrayList = userArrayList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView txtQues;
        RecyclerView optRecyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtQues = itemView.findViewById(R.id.txtQues);
            optRecyclerView = itemView.findViewById(R.id.optRecyclerView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.activity_question,parent,false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.setIsRecyclable(false);

        Questionnaire.QuestionsItem ques = quesArrayList.get(position);

        holder.txtQues.setText(ques.getQuestion());

        ArrayList<Questionnaire.QuestionsItem.OptionsItem> optArrayList = new ArrayList<>();

        for (int i = 0; i < ques.getOptions().size(); i++){
            optArrayList.add(ques.getOptions().get(i));
        }
        OptionAdapter optionAdapter = new OptionAdapter(context,optArrayList, ques);
        holder.optRecyclerView.setLayoutManager(new LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL,true));
        holder.optRecyclerView.setAdapter(optionAdapter);
    }

    @Override
    public int getItemCount() {
        return quesArrayList.size();
    }
}
