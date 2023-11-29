package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Hello Shady
        EditText username = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        Button loginBtn = findViewById(R.id.login_button);


        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // Username must equal admin
                // Password must equal 1234
                if(username.getText().equals("admin") && password.getText().equals("1234")){
                    Toast.makeText(Login.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Login.this, "INCORRECT PASSWORD. HINT (admin, 1234)", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
