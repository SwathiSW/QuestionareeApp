package com.example.starwardemo.adapters;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starwardemo.R;
import com.example.starwardemo.model;

import java.util.ArrayList;
import java.util.List;

public class name_adapter extends RecyclerView.Adapter <name_adapter.viewholder>{
    Context context;
    List<model> al;

    public name_adapter(Context context, List<model> al){
        this.context = context;
        this.al = al;
    }
    public static class viewholder extends  RecyclerView.ViewHolder{
        TextView name, dob;
        ConstraintLayout layout;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView);
            layout = itemView.findViewById(R.id.layout);
            dob = itemView.findViewById(R.id.dob);
        }

    }

    public void setData(List<model> al2){
        this.al = al2 ;
        notifyDataSetChanged();
    }

    public void filter(ArrayList<model> nm){
        al = nm;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public name_adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.name_layout, parent, false);
        viewholder viewholder = new viewholder(view);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull name_adapter.viewholder holder, int position) {

        holder.name.setText(al.get(position).getName());

        holder.dob.setText(al.get(position).getBirth_year());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, MainActivity3.class);
                i.putExtra("name", al.get(position).getName()).putExtra("skin", al.get(position).getSkin_color())
                        .putExtra("hair", al.get(position).hair_color).putExtra("height", al.get(position).getHeight())
                        .putExtra("mass", al.get(position).getMass()).putExtra("eye", al.get(position).getEye_color())
                        .putExtra("homeworld", al.get(position).getHomeworld()).putExtra("gender", al.get(position).getGender())
                        .putExtra("dob", al.get(position).getBirth_year()).putExtra("films", al.get(position).getArr()).putExtra("url", al.get(position).getUrl());

                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        Log.d("Hddd", "getItemCount: "+al.size());
        return al.size();
    }

}
