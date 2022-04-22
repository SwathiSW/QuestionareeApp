package com.example.samplequestionaryapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.samplequestionaryapp.R;
import com.example.samplequestionaryapp.database.UserDao;
import com.example.samplequestionaryapp.database.UserDatabase;
import com.example.samplequestionaryapp.database.UserEntity;

public class RegistrationActivity extends AppCompatActivity  {
    TextView tv_registrationPage;
    EditText et_Email,et_Password,et_confirmPassword,et_Phone,et_streetName,et_city,et_state,et_zipcode;
    Button btn_register;
    Spinner spinner;
    TextView tv_forgotPassword,tv_regLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        tv_registrationPage = (TextView) findViewById(R.id.tv_registrationPage);
        et_Email = (EditText) findViewById(R.id.et_Email);
        et_Password = (EditText) findViewById(R.id.et_Password);
        et_confirmPassword = (EditText) findViewById(R.id.et_confirmPassword);
        et_Phone = (EditText) findViewById(R.id.et_Phone);
        et_streetName = (EditText) findViewById(R.id.et_streetName);
        et_city = (EditText) findViewById(R.id.et_city);
        et_state = (EditText) findViewById(R.id.et_state);
        et_zipcode = (EditText) findViewById(R.id.et_zipcode);
        tv_forgotPassword = (TextView) findViewById(R.id.tv_forgotPassword);
        tv_regLogin = (TextView) findViewById(R.id.tv_regLogin);
        spinner = (Spinner) findViewById(R.id.spinner);
        btn_register = (Button) findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Creating User Entity
                UserEntity userEntity = new UserEntity();
               userEntity.setEt_email(et_Email.getText().toString());
               userEntity.setEt_password(et_Password.getText().toString());
               userEntity.setPhone(et_Phone.getText().toString());
               userEntity.setState(et_state.getText().toString());
               userEntity.setCity(et_city.getText().toString());
               userEntity.setZipcode(et_zipcode.getText().toString());

                String email = et_Email.getText().toString().trim();
                String password = et_Password.getText().toString().trim();
                String confirmPassword = et_confirmPassword.getText().toString().trim();
                String Phone = et_Phone.getText().toString().trim();
                String state = et_state.getText().toString().trim();
                String streetName = et_streetName.getText().toString().trim();
                String city = et_city.getText().toString().trim();
                String zipcode = et_zipcode.getText().toString().trim();

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    et_Email.setError("Please provide valid Email Address");
                    et_Email.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    et_Password.setError("Password is required");
                    et_Password.requestFocus();
                    return;
                }
                if (password.length() < 6) {
                    et_Password.setError("Min password length should be 6 characters!");
                    et_Password.requestFocus();
                    return;
                }
                if (!TextUtils.isEmpty(password) && !TextUtils.isEmpty(confirmPassword)) {
                    if (password.equals(confirmPassword)) {
                        //et_confirmPassword.setText("same");
                    } else {
                        et_confirmPassword.setError("Confirm Password should same like Password");
                    }
                }
                if (!Patterns.PHONE.matcher(Phone).matches()) {
                    et_Phone.setError("Please provide valid Phone number");
                    et_Phone.requestFocus();
                    return;
                }
                if (streetName.isEmpty()) {
                    et_streetName.setError("streetName is required");
                    et_streetName.requestFocus();
                    return;
                }
                if (city.isEmpty()) {
                    et_city.setError("City is required");
                    et_city.requestFocus();
                    return;
                }
                if (state.isEmpty()) {
                    et_state.setError("state is required");
                    et_state.requestFocus();
                    return;
                }
                if (zipcode.length() < 6) {
                    et_zipcode.setError("Min password length should be 6 characters!");
                    et_zipcode.requestFocus();
                    return;
                }
                UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                final UserDao userDao = userDatabase.userDao();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        userDao.registerUser(userEntity);
//                        Log.d("userEntity",userEntity + " Entity");
  //                      userDao.registerUser(userEntity);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegistrationActivity.this, "User Registered!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).start();
            }
        });
        tv_regLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.spinnerlist, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
    }
}