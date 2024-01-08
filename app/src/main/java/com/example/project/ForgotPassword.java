package com.example.project;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class ForgotPassword extends AppCompatActivity {
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        mAuth = FirebaseAuth.getInstance();
        TextView email = findViewById(R.id.email);
        Button reset = findViewById(R.id.reset_button);

        reset.setOnClickListener(view -> {
            String emailText = email.getText().toString().trim();


        });

    }
}