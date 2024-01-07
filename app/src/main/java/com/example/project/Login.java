package com.example.project;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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
