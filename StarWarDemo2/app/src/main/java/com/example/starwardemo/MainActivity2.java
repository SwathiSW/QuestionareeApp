package com.example.starwardemo.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.example.starwardemo.R;
import com.example.starwardemo.model;
import com.example.starwardemo.name_adapter;
import com.example.starwardemo.name_viewmodel;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private static final String TAG = "TAGGG";
    model nm;
    RecyclerView rec;
    model mod;
    List<model> nl ;
    EditText name;
    name_viewmodel view_model;
    name_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        rec = findViewById(R.id.rec);
        rec.setLayoutManager(new LinearLayoutManager(this));
        rec.setHasFixedSize(true);
        nl = new ArrayList<>();
        name = findViewById(R.id.name);

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(name.getText().toString());
            }
        });

        name.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (name.getRight() - name.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        //Perform Action

                        return true;
                    }
                }
                return false;
            }
        });


        view_model = ViewModelProviders.of(this).get(name_viewmodel.class);
        view_model.getName_model().observe(this, new Observer<model>() {
            @Override
            public void onChanged(model model) {
                if(model != null){
                    mod = model;
                    adapter.setData(nl);
                    nl.add(mod);
                }
                else{

                }

            }
        });
        view_model.makeApiCall();

        adapter = new name_adapter(MainActivity2.this, nl);
        rec.setAdapter(adapter);


    }

    public void filter(String name){
        ArrayList<model> nm = new ArrayList<>();
        for(model nm2 : nl){
            if(nm2.getName().toLowerCase().contains(name.toLowerCase())){
                nm.add(nm2);

            }
            else {

            }
        }
        if(nm.isEmpty()){

        }
        else{
            adapter.filter(nm);
        }

    }

}
