package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Hello Shady
        TextView username = (TextView) findViewById(R.id.email);
        TextView password = (TextView) findViewById(R.id.password);
        Button loginBtn = (Button) findViewById(R.id.login_button);


        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // Username must equal admin
                // Password must equal 1234
                if(username.getText().toString().trim().equals("admin") && password.getText().toString().trim().equals("1234")){
                    // Correct input, show message then move to home page
                    Toast.makeText(Login.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(Login.this, Home.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 2000);
                }
                else{
                    // Incorrect message, show message and do nothing
                    Toast.makeText(Login.this, "INCORRECT PASSWORD. HINT (admin, 1234)", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
