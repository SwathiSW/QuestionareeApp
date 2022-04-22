package com.example.samplequestionaryapp.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    public int Id;

    @ColumnInfo(name = "et_email")
    String et_email;

    @ColumnInfo(name = "et_password")
    String et_password;

    @ColumnInfo(name = "Phone")
    String phone;

    @ColumnInfo(name = "City")
    String city;

    @ColumnInfo(name = "state")
    String state;

    @ColumnInfo(name = "Zipcode")
    String Zipcode;

    @ColumnInfo(name = "United state")
    String unitedstate;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getEt_email() {
        return et_email;
    }

    public void setEt_email(String et_email) {
        this.et_email = et_email;
    }

    public String getEt_password() {
        return et_password;
    }

    public void setEt_password(String et_password) {
        this.et_password = et_password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return Zipcode;
    }

    public void setZipcode(String zipcode) {
        Zipcode = zipcode;
    }

    public String getUnitedstate() {
        return unitedstate;
    }

    public void setUnitedstate(String unitedstate) {
        this.unitedstate = unitedstate;
    }
}