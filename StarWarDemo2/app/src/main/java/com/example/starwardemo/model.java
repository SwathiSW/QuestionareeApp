package com.example.starwardemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class model {
    String name, birth_year,height, language, population, homeworld, opening_crawl, gender, hair_color, skin_color, mass, eye_color, url;
    @SerializedName("films")
    ArrayList<String> arr;

    public model(){

    }

    public model(String name, String birth_year, String height, String language, String population, String homeworld, String opening_crawl, String gender, String hair_color, String skin_color, String mass, String eye_color, ArrayList<String> arr, String url) {
        this.name = name;
        this.birth_year = birth_year;
        this.height = height;
        this.language = language;
        this.population = population;
        this.homeworld = homeworld;
        this.opening_crawl = opening_crawl;
        this.gender = gender;
        this.hair_color = hair_color;
        this.skin_color = skin_color;
        this.mass = mass;
        this.eye_color = eye_color;
        this.arr = arr;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHair_color() {
        return hair_color;
    }

    public void setHair_color(String hair_color) {
        this.hair_color = hair_color;
    }

    public String getSkin_color() {
        return skin_color;
    }

    public void setSkin_color(String skin_color) {
        this.skin_color = skin_color;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getEye_color() {
        return eye_color;
    }

    public void setEye_color(String eye_color) {
        this.eye_color = eye_color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(String birth_year) {
        this.birth_year = birth_year;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getHomeworld() {
        return homeworld;
    }

    public void setHomeworld(String homeworld) {
        this.homeworld = homeworld;
    }

    public String getOpening_crawl() {
        return opening_crawl;
    }

    public void setOpening_crawl(String opening_crawl) {
        this.opening_crawl = opening_crawl;
    }

    public ArrayList<String> getArr() {
        return arr;
    }

    public void setArr(ArrayList<String> arr) {
        this.arr = arr;
    }
}
