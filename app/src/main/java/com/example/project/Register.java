package com.example.project;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        TextView username = findViewById(R.id.email); // Username field
        TextView password = findViewById(R.id.password); // Password field
        Button regBtn = findViewById(R.id.signup_button); // Register Button
        Button loginBtn = findViewById(R.id.login_button); // Switch to Login view

        regBtn.setOnClickListener(view -> { // password and username validation without regex

            // Username and Password are extracted and trimmed
            String usernameText = username.getText().toString().trim();
            String passwordText = password.getText().toString().trim();

            if(passwordText.length() < 6){
                Toast.makeText(Register.this, "Password must be 6 characters or more", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.createUserWithEmailAndPassword(usernameText, passwordText)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            new Handler().postDelayed(() -> {
                                Intent intent = new Intent(Register.this, Home.class);
                                startActivity(intent);
                                finish();
                            }, 2000);
                        } else {
                            // If sign in fails, display a message to the user.

                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Register.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    });

            Pattern p1 = Pattern.compile("[a-zA-z]{4,20}"); // Between 4 and 20 alphabetical letters
            Pattern p2 = Pattern.compile("[a-zA-z0-9]{8,}"); // At least 8 alphanumerical characters

            // Checking if they match the regex
//            Matcher m1 = p1.matcher(usernameText);
//            Matcher m2 = p2.matcher(passwordText);

//            if(m1.matches()){ // Username matches
//                if(m2.matches()){ // Password matches
//                    Toast.makeText(Register.this, "REGISTRATION SUCCESSFUL", Toast.LENGTH_SHORT).show();
//                    // Go to Home Page
//                    new Handler().postDelayed(() -> {
//                        Intent intent = new Intent(Register.this, Home.class);
//                        startActivity(intent);
//                        finish();
//                    }, 2000);
//                }
//                else{
//                    Toast.makeText(Register.this, "PASSWORD MUST BE 8 CHARACTERS OR MORE (alphanumerical characters)", Toast.LENGTH_SHORT).show();
//                }
//            }
//            else{
//                Toast.makeText(Register.this, "USERNAME MUST BE BETWEEN 4 AND 20 CHARACTERS AND CONTAIN ONLY ALPHABETICAL LETTERS", Toast.LENGTH_SHORT).show();
//            }


        });

        // Switch to login view
        loginBtn.setOnClickListener(view -> {
            Intent intent = new Intent(Register.this, Login.class);
            startActivity(intent);
        });

    }
}