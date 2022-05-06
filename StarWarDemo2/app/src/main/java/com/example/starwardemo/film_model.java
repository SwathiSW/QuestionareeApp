package com.example.starwardemo.model;

import com.google.gson.annotations.SerializedName;

public class film_model {
    String title;

    @SerializedName("opening_crawl")
    String description;

    public film_model(){

    }

    public film_model(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
