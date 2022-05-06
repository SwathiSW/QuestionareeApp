package com.example.starwardemo.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.starwardemo.Api_Interface;
import com.example.starwardemo.Retrofit_Instance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class name_viewmodel extends ViewModel {
    int i = 1;

    private MutableLiveData<model> model;

    public name_viewmodel(){
        model = new MutableLiveData<>();
    }

    public  MutableLiveData<model> getName_model(){
        return model;
    }

    public void makeApiCall(){
        Api_Interface inter = Retrofit_Instance.getRetrofit().create(Api_Interface.class);

        for (i = 1; i <= 82; i++) {
            Call<model> call = inter.getData(i);
            call.enqueue(new Callback<com.example.starwardemo.model>() {
                @Override
                public void onResponse(Call<com.example.starwardemo.model> call, Response<com.example.starwardemo.model> response) {

                    if(response.isSuccessful()){
                        model.postValue(response.body());
                    }

                }

                @Override
                public void onFailure(Call<com.example.starwardemo.model> call, Throwable t) {
                    model.postValue(null);

                }
            });

        }

    }
}
