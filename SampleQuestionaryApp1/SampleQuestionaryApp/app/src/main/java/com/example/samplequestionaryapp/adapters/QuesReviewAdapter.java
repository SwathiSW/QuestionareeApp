package com.example.samplequestionaryapp.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samplequestionaryapp.R;
import com.example.samplequestionaryapp.model.Questionnaire;

import java.util.ArrayList;

public class QuesReviewAdapter extends RecyclerView.Adapter<QuesReviewAdapter.ViewHolder> {

    Activity context;
    ArrayList<Questionnaire.QuestionsItem> quesArrayList;

    public QuesReviewAdapter(Activity context, ArrayList<Questionnaire.QuestionsItem> userArrayList, int eachQuesMarks) {
        this.context = context;
        this.quesArrayList = userArrayList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView txtQuesRev;
        AppCompatTextView txtUserAns;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtQuesRev = itemView.findViewById(R.id.txtQuesRev);
            txtUserAns = itemView.findViewById(R.id.txtUserAns);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_questions_review,parent,false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.setIsRecyclable(false);

        Questionnaire.QuestionsItem ques = quesArrayList.get(position);

        ArrayList<Questionnaire.QuestionsItem.OptionsItem> optArrayList = new ArrayList<>();

        for (int i = 0; i < ques.getOptions().size(); i++){
            optArrayList.add(ques.getOptions().get(i));

            if (optArrayList.get(i).getOptionId() == ques.getCorrectOption().get(0)){

                holder.txtQuesRev.setText(ques.getQuestion());
                holder.txtUserAns.setText(""+optArrayList.get(i).getOption());
            }

        }

    }

    @Override
    public int getItemCount() {
        return quesArrayList.size();
    }
}
