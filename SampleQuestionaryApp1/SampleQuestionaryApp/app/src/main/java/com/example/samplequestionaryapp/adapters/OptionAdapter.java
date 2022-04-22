package com.example.samplequestionaryapp.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samplequestionaryapp.R;
import com.example.samplequestionaryapp.model.Questionnaire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.ViewHolder> {

    Activity context;
    ArrayList<Questionnaire.QuestionsItem.OptionsItem> optArrayList;
    List<Integer> opArr = new ArrayList<>();
    int[] intArray = new int[0];

    Questionnaire.QuestionsItem quesPerPos;

    private int selectedItemPosition = -1;

    public OptionAdapter(Activity context,
                         ArrayList<Questionnaire.QuestionsItem.OptionsItem> optArrayList, Questionnaire.QuestionsItem quesPerPos) {
        this.context = context;
        this.optArrayList = optArrayList;
        this.quesPerPos = quesPerPos;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView txtOpt, txtOpt1;
        ConstraintLayout checkLayout, radioLayout;
        RadioButton optRadBtn;
        CheckBox optCheckbox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtOpt = itemView.findViewById(R.id.txtOpt);
            txtOpt1 = itemView.findViewById(R.id.txtOpt1);
            checkLayout = itemView.findViewById(R.id.checkLayout);
            radioLayout = itemView.findViewById(R.id.radioLayout);
            optRadBtn = itemView.findViewById(R.id.optRadBtn);
            optCheckbox = itemView.findViewById(R.id.optCheckbox);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.option_layout,parent,false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.setIsRecyclable(false);

        Questionnaire.QuestionsItem.OptionsItem option = optArrayList.get(position);

        if (quesPerPos.getQuesType() == 1) {
            holder.checkLayout.setVisibility(View.GONE);
            holder.radioLayout.setVisibility(View.VISIBLE);

            holder.txtOpt1.setText(option.getOption());

            holder.optRadBtn.setOnCheckedChangeListener(null);

            if (option.getOptionId() == quesPerPos.getUserOpt()) {
                holder.optRadBtn.setChecked(true);
                option.setIsChecked(1);
            } else {
                holder.optRadBtn.setChecked(false);
                option.setIsChecked(0);
            }
            holder.radioLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.optRadBtn.setChecked(true);
                    option.setIsChecked(1);

                    quesPerPos.setUserOpt(option.getOptionId());

                    selectedItemPosition = holder.getAdapterPosition();
                    notifyDataSetChanged();
                }
            });

            holder.optRadBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    holder.optRadBtn.setChecked(true);
                    option.setIsChecked(1);

                    quesPerPos.setUserOpt(option.getOptionId());

                    selectedItemPosition = holder.getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
        }else {
            holder.radioLayout.setVisibility(View.GONE);
            holder.checkLayout.setVisibility(View.VISIBLE);

            holder.txtOpt.setText(option.getOption());

            holder.optCheckbox.setOnCheckedChangeListener(null);

            if (quesPerPos.getUserOptCheck() != null) {
                for (int i = 0; i < quesPerPos.getUserOptCheck().size(); i++) {
                    if (option.getOptionId() == quesPerPos.getUserOptCheck().get(i)) {
                        holder.optCheckbox.setChecked(true);
                        option.setIsChecked(1);
                    }
                }
            }

            holder.checkLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.optCheckbox.setChecked(true);
                    option.setIsChecked(1);

                    if (quesPerPos.getUserOptCheck() != null) {
                        opArr.addAll(quesPerPos.getUserOptCheck());
                    }
                    opArr.add(option.getOptionId());

                    quesPerPos.setUserOptCheck(opArr);

                    Log.e("optCheckLIst", ""+opArr);

                    notifyDataSetChanged();
                }
            });

            holder.optCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    holder.optCheckbox.setChecked(true);
                    option.setIsChecked(1);

                    if (quesPerPos.getUserOptCheck() != null) {
                        opArr.addAll(quesPerPos.getUserOptCheck());
                    }
                    opArr.add(option.getOptionId());
                    quesPerPos.setUserOptCheck(opArr);
                    Log.e("optCheckLIst1", ""+opArr);

                    notifyDataSetChanged();
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return optArrayList.size();
    }
}
