package com.firebaseauth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebaseauth.firebaseauth.Login;
import com.firebaseauth.firebaseauth.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    TextView Register;
    Button btn_Login;
    TextView etUsername,etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUsername = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        btn_Login = findViewById(R.id.login);
        Register = findViewById(R.id.signUpText);
        mAuth = FirebaseAuth.getInstance();

        // Calling on com.example.Login button
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                // Validation Checking
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    etUsername.setError("Please provide valid Email Address");
                    etUsername.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    etPassword.setError("Password is required");
                    etPassword.requestFocus();
                    return;
                }
                if (password.length() < 6) {
                    etPassword.setError("Min password length should be 6 characters!");
                    etPassword.requestFocus();
                    return;
                }
                // Authenticate the com.example.User

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);

                            Toast.makeText(MainActivity.this, "com.example.Login successfully",
                                    Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.firebaseauth.firebaseauth.Register.class);
                startActivity(intent);
            }
        });
    }
}