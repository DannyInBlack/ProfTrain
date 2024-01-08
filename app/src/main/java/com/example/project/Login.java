package com.example.project;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.Manifest;
import android.app.NotificationManager;
import android.app.NotificationChannel;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        TextView username = findViewById(R.id.email);// Email field
        TextView password = findViewById(R.id.password); // Password field
        Button loginBtn = findViewById(R.id.login_button); // Login Button
        Button regBtn = findViewById(R.id.register); // Switch to Register View

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if (ActivityCompat.checkSelfPermission(this.getApplicationContext(), android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Login.this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1
                );
            }
        }




        loginBtn.setOnClickListener(view -> {

            String user = username.getText().toString().trim();
            String pass = password.getText().toString().trim();

            mAuth.signInWithEmailAndPassword(user, pass)
                    .addOnCompleteListener(this, task -> {
                        // Successful login, move user to home page
                        if(task.isSuccessful()){
                            Log.d(TAG, "login:success");
                            FirebaseUser user1 = mAuth.getCurrentUser();
                            Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                            new Handler().postDelayed(() -> {
                                Intent intent = new Intent(Login.this, Home.class);
                                startActivity(intent);
                                finish();
                            }, 2000);
                        }
                        // Unsuccessful login, display error to user;
                        else{
                            Log.w(TAG, "login:failure", task.getException());
                            Toast.makeText(Login.this, "Login failure", Toast.LENGTH_SHORT).show();
                        }
                    });


        });

        // Switch to register view
        regBtn.setOnClickListener(view -> {
            Intent intent = new Intent(Login.this, Register.class);
            startActivity(intent);
        });

    }


}
