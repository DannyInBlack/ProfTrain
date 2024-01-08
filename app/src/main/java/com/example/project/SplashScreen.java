package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mAuth = FirebaseAuth.getInstance();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Switch to Login after 3 seconds of splash screen
                Intent intent;
                if(mAuth.getCurrentUser() != null)
                    intent = new Intent(SplashScreen.this, Home.class);
                else
                    intent = new Intent(SplashScreen.this, Login.class);

                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}