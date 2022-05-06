package com.example.starwardemo.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starwardemo.R;
import com.example.starwardemo.film_model;

import java.util.List;

public class film_adapter extends RecyclerView.Adapter <film_adapter.viewholder>{
    Context context;
    List<film_model> al;

    public film_adapter(Context context, List<film_model> al){
        this.context = context;
        this.al = al;
    }
    public static class viewholder extends  RecyclerView.ViewHolder{
        TextView title, des;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.film_title);
            des = itemView.findViewById(R.id.des);
        }

    }

    @NonNull
    @Override
    public film_adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.film_layout, parent, false);
        viewholder viewholder = new viewholder(view);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull film_adapter.viewholder holder, int position) {
        holder.title.setText(al.get(position).getTitle());
        holder.des.setText(al.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return al.size();
    }

}
