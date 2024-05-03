package com.capstone.aiahssweettreat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Signup extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        Button signUpButton = findViewById(R.id.btnlogin);
        TextInputEditText nameEditText = findViewById(R.id.tvname);
        TextInputEditText addressEditText = findViewById(R.id.tvaddress);
        TextInputEditText emailEditText = findViewById(R.id.tvemail);
        TextInputEditText passwordEditText = findViewById(R.id.tvpassword);
        TextInputEditText phonenumberEditText = findViewById(R.id.tvphonenumber);


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString().trim();
                String address = addressEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String phonenumber = phonenumberEditText.getText().toString().trim();

                // Perform validation checks on the input fields

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    String userId = user.getUid();

                                    Map<String, Object> userData = new HashMap<>();
                                    userData.put("uid", userId);
                                    userData.put("name", name);
                                    userData.put("address", address);
                                    userData.put("email", email);
                                    userData.put("phonenumber", phonenumber);

                                    db.collection("users").document().set(userData)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(Signup.this, "User created successfully", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(Signup.this, Login.class);
                                                    startActivity(intent);
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {

                                                }
                                            });
                                } else {
                                    Toast.makeText(Signup.this, "User registration failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }
}