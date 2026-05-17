package com.example.roomfinder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

    public class RegisterActivity extends AppCompatActivity {

        EditText emailEditText, passwordEditText;
        Button registerButton;
        TextView loginText;
        SharedPreferences sharedPreferences;

        private static final String PREF_NAME = "user_data";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);

            sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

            emailEditText = findViewById(R.id.registerEmail);
            passwordEditText = findViewById(R.id.registerPassword);
            registerButton = findViewById(R.id.registerBtn);
            loginText = findViewById(R.id.goToLogin);

            registerButton.setOnClickListener(v -> {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("email", email);
                    editor.putString("password", password);
                    editor.apply();

                    Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                }
            });

            loginText.setOnClickListener(v -> {
                startActivity(new Intent(this, LoginActivity.class));
            });
        }
    }
