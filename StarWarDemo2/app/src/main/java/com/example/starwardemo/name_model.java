package com.example.starwardemo.model;
import com.google.gson.annotations.SerializedName;

public class name_model {
    String name, url;

    @SerializedName("birth_year")
    String DOB;

    public name_model(){

    }

    public name_model(String name, String DOB, String url) {
        this.name = name;
        this.DOB = DOB;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }
}

