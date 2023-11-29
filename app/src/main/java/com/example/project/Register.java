package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        String username = ((TextView) findViewById(R.id.email)).getText().toString().trim();
        String password = ((TextView) findViewById(R.id.password)).getText().toString().trim();
        Button regBtn = findViewById(R.id.signup_button);
        Button loginBtn = findViewById(R.id.login_button);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // password and username validation without regex
                if(username.length() > 4 && username.length() <= 20){
                    if(password.length() >= 8){
                        Toast.makeText(Register.this, "REGISTRATION SUCCESSFUL", Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(Register.this, Home.class);
                                startActivity(intent);
                                finish();
                            }
                        }, 2000);
                    }
                    else{
                        Toast.makeText(Register.this, "PASSWORD MUST BE 8 CHARACTERS OR MORE", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(Register.this, "USERNAME MUST BE BETWEEN 4 AND 20 CHARACTERS", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() { // Switch to login view
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });

    }
}