package com.example.samplequestionaryapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface  UserDao {

    @Insert
     void registerUser(UserEntity userEntity);

    @Query("SELECT * from users where et_email=(:et_Email) and " + "et_password=(:et_Password)")
    UserEntity registerUser(String et_Email, String et_Password);


    @Query("SELECT * from users where et_email=(:et_email) and et_password=(:et_password)")
    List<UserEntity> login(String et_email, String et_password);

}

