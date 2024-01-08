package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends AppCompatActivity {
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_profile);
        mAuth = FirebaseAuth.getInstance();
        Button logoutBtn = findViewById(R.id.logout_button);
        FirebaseAuth.AuthStateListener mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // Sign in logic here.
                    Log.d("user", user.toString());

                }
                else{
                    Intent intent = new Intent(Profile.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        mAuth.addAuthStateListener(mAuthListener);

        logoutBtn.setOnClickListener(view -> {
            mAuth.signOut();
        });
    }
}
