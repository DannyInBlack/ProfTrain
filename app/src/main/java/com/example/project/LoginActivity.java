package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username = findViewById(R.id.email);
    EditText password = findViewById(R.id.password);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        Button loginBtn = (Button) findViewById(R.id.login_button);


        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(username.getText().equals("admin") && password.getText().equals("1234")){
                    Toast.makeText(LoginActivity.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(LoginActivity.this, "INCORRECT PASSWORD. HINT (admin, 1234)", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
