package com.firebaseauth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebaseauth.firebaseauth.MainActivity;
import com.firebaseauth.firebaseauth.R;
import com.firebaseauth.firebaseauth.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    //Creating Member variable for firebaseAuth
    private FirebaseAuth mAuth;
    //Creating Member variable for firebase store
    private FirebaseFirestore fstore;
    //Creating Member Variable for firebase Database
    private DatabaseReference databaseUser;

    private TextView etFullname;
    private TextView etAge;
    private TextView etEmail;
    private TextView etpassword;
    private Button Reg;
    private String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        databaseUser = FirebaseDatabase.getInstance().getReference("com.example.User");

        etFullname = (TextView) findViewById(R.id.etFullname);
        etAge = (TextView) findViewById(R.id.etAge);
        etEmail = (TextView) findViewById(R.id.etEmail);
        etpassword = (TextView) findViewById(R.id.etpassword);
        Reg = (Button) findViewById(R.id.Reg);

        // Calling on com.example.Register Clicked Button
        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = etEmail.getText().toString().trim();
                String Fullname = etFullname.getText().toString().trim();
                String Age = etAge.getText().toString().trim();
                String password = etpassword.getText().toString().trim();

                //Validation Checking

                if (Fullname.isEmpty()) {
                    etFullname.setError("Fullname is required");
                    etFullname.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    etEmail.setError("Please provide valid Email Address");
                    etEmail.requestFocus();
                    return;
                }
                if (Age.isEmpty()) {
                    etAge.setError("Age is required");
                    etAge.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    etpassword.setError("Password is required");
                    etpassword.requestFocus();
                    return;
                }
                if (password.length() < 6) {
                    etpassword.setError("Min password length should be 6 characters!");
                    etpassword.requestFocus();
                    return;
                }

                // com.example.Register the com.example.User in Firebase
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            String id = databaseUser.push().getKey();
                            User user = new User(Fullname,Age,email);
                            databaseUser.child(id).setValue(user);
                            Toast.makeText(Register.this, "com.example.User has been registered successfully!",
                                    Toast.LENGTH_LONG).show();

                            userID = mAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("users").document(userID);
                            Map<String, Object> Person = new HashMap<>();
                            Person.put("fullname", Fullname);
                            Person.put("email", email);
                            Person.put("Age", Age);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d("TAG", "onSuccess: user profile is created for " + userID);
                                }
                            });
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Register.this, "You are not Registered! Try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
//    Reg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(),com.example.MainActivity.class);
//               startActivity(intent);
//          }
//       };
}