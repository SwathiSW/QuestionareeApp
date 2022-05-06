package com.example.starwardemo.activity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.starwardemo.Api_Interface_Homeworld;
import com.example.starwardemo.Api_Interface_films;
import com.example.starwardemo.Homeworld_model;
import com.example.starwardemo.R;
import com.example.starwardemo.film_adapter;
import com.example.starwardemo.film_model;
import com.example.starwardemo.getId;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity3 extends AppCompatActivity {
    TextView a_name, a_skin, a_hair, a_height, a_mass, a_eye, a_homeworld, a_gender, a_birth;
    String name, skin, hair, height, eye, mass, gender, birth, homeworld, url;
    ArrayList<String> films = new ArrayList<>();
    List<film_model> fl;
    RecyclerView rec;
    film_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Character Details");

        a_name = findViewById(R.id.a_name);
        a_skin = findViewById(R.id.a_skin);
        a_hair = findViewById(R.id.a_hair);
        a_height = findViewById(R.id.a_height);
        a_mass = findViewById(R.id.a_mass);
        a_eye = findViewById(R.id.a_eye);
        a_homeworld = findViewById(R.id.a_homeworld);
        a_gender = findViewById(R.id.a_gender);
        a_birth = findViewById(R.id.a_birth);

        name = getIntent().getStringExtra("name");
        skin = getIntent().getStringExtra("skin");
        hair = getIntent().getStringExtra("hair");
        height = getIntent().getStringExtra("height");
        mass = getIntent().getStringExtra("mass");
        eye = getIntent().getStringExtra("eye");
        gender = getIntent().getStringExtra("gender");
        birth = getIntent().getStringExtra("dob");
        url = getIntent().getStringExtra("url");
        films = getIntent().getStringArrayListExtra("films");

        getHomeWorld(getIntent().getStringExtra("homeworld"));

        a_name.setText(name);
        a_skin.setText(skin);
        a_hair.setText(hair);
        a_height.setText(height);
        a_gender.setText(gender);
        a_eye.setText(eye);
        a_birth.setText(birth);
        a_mass.setText(mass);
        a_homeworld.setText(homeworld);

        //setting recyclerview
        rec = findViewById(R.id.film_rec);
        rec.setHasFixedSize(true);
        rec.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        fl = new ArrayList<>();

        getFilms();

    }

    private void getFilms() {


        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://swapi.dev/api/")
                .build();

        Api_Interface_films inter = retrofit.create(Api_Interface_films.class);

        for (int i = 0; i < films.size(); i++) {
            Call<film_model> call = inter.getData(new getId().get(films.get(i)));
            call.enqueue(new Callback<film_model>() {
                @Override
                public void onResponse(Call<film_model> call, Response<film_model> response) {

                    if (response.isSuccessful()) {
                        film_model model = new film_model(response.body().getTitle(), response.body().getDescription());
                        fl.add(model);
                        adapter = new film_adapter(MainActivity3.this, fl);
                        rec.setAdapter(adapter);

                    }

                }

                @Override
                public void onFailure(Call<film_model> call, Throwable t) {

                }
            });

        }

    }

    private void getHomeWorld(String homeworld) {

        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://swapi.dev/api/")
                .build();

        Api_Interface_Homeworld inter = retrofit.create(Api_Interface_Homeworld.class);

        Call<Homeworld_model> call = inter.getData(new getId().get(url));

        call.enqueue(new Callback<Homeworld_model>() {
            @Override
            public void onResponse(Call<Homeworld_model> call, Response<Homeworld_model> response) {

                if (response.isSuccessful()) {
                    a_homeworld.setText(response.body().getName());
                }
            }

            @Override
            public void onFailure(Call<Homeworld_model> call, Throwable t) {
                a_homeworld.setText("Error");
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(new Intent(MainActivity3.this, MainActivity2.class));
        return true;
    }
}