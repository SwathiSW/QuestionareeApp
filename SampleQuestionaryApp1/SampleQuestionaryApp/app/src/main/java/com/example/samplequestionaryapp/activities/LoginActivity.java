package com.example.samplequestionaryapp.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.samplequestionaryapp.R;
import com.example.samplequestionaryapp.database.UserDao;
import com.example.samplequestionaryapp.database.UserDatabase;
import com.example.samplequestionaryapp.database.UserEntity;

import java.util.List;

public class LoginActivity extends AppCompatActivity  {
    EditText et_email;
    EditText et_password;
    Button btn_login;
    Button btn_signIn;
    TextView tv_forgotPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_email = (EditText) findViewById(R.id.et_email1);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);
         btn_signIn = (Button) findViewById(R.id.btn_signIn);
         tv_forgotPassword = (TextView) findViewById(R.id.tv_forgotPassword);


        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(intent);
            }
        });
//      Calling on sign_in button

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_email.getText().toString().trim();
                String password = et_password.getText().toString().trim();

                // Validation Checking
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    et_email.setError("Please provide valid Email Address");
                    et_email.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    et_password.setError("Password is required");
                    et_password.requestFocus();
                    return;
                }
                if (password.length() < 6) {
                    et_password.setError("Min password length should be 6 characters!");
                    et_password.requestFocus();
                    return;
                } else {
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    UserDao userDao = userDatabase.userDao();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            List<UserEntity> userEntity = userDao.login(email, password);
                           // Log.d("userEntity",userEntity.getEt_email() + " Entity");
                            if (userEntity.size() == 0) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(LoginActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("testSubmitted",0);
                                startActivity(intent);
                                finish();
                            }
                        }
                    }).start();
                }
            }

        });
       tv_forgotPassword.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               showDialog(LoginActivity.this);
           }
       });
    }

    public void showDialog(Activity activity){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.layout_dialog);

        EditText et_email1 = dialog.findViewById(R.id.et_email1);
        Button btn_submit = dialog.findViewById(R.id.btn_submit);
        Button btn_cancel = dialog.findViewById(R.id.btn_cancel);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(et_email1.getText().toString())) {
                    et_email1.setError("Please Enter EmailID!");
                    //Toast.makeText(activity, "Please Enter EmailID",Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(activity, "" + et_email1.getText().toString(),Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
    }
}
