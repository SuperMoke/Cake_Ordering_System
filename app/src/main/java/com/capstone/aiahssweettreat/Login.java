package com.capstone.aiahssweettreat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;

public class Login extends AppCompatActivity {

    private EditText email, password;
    private Button login;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        email =  findViewById(R.id.tvemail);
        password = findViewById(R.id.tvpassword);
        login = findViewById(R.id.btnlogin);
        TextView tvDontHaveAccount = findViewById(R.id.tvDontHaveAccount); // Replace with your TextView's ID

        String text = "Don't have an account? Click here";
        SpannableString spannableString = new SpannableString(text);

// Set the "Click here" text color to blue
        int clickHereStart = text.indexOf("Click here");
        int clickHereEnd = clickHereStart + "Click here".length();
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorBlue)), clickHereStart, clickHereEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

// Make the "Click here" text clickable
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
            }
        }, clickHereStart, clickHereEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvDontHaveAccount.setText(spannableString);
        tvDontHaveAccount.setMovementMethod(LinkMovementMethod.getInstance());


        Intent intent = new Intent(this,admin_homepage.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = email.getText().toString().trim();
                String userpassword = password.getText().toString().trim();


                if(useremail.isEmpty() || userpassword.isEmpty()){
                    Toast.makeText(Login.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
                if(useremail.equals("admin") && userpassword.equals("admin123")){
                    startActivity(intent);
                }
                else mAuth.signInWithEmailAndPassword(useremail,userpassword)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(Login.this, "Authentication Success", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Login.this,user_homepage.class));
                                    finish();
                                } else {
                                    Toast.makeText(Login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }
}