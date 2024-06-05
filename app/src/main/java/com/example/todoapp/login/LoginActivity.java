package com.example.todoapp.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todoapp.MainActivity;
import com.example.todoapp.R;

public class LoginActivity extends AppCompatActivity {


    public static final String SHARED_PREFS = "shared_prefs";


    public static final String USERNAME_KEY = "username_keu";

    public static final String PASSWORD_KEY = "password_key";



    SharedPreferences sharedpreferences;

    EditText edtUserName, edtPassword;
    Button btnLogin;

    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        edtUserName = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        username = sharedpreferences.getString("USERNAME_KEY", null);
        password = sharedpreferences.getString("PASSWORD_KEY", null);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                if (edtUserName.getText().toString().trim().equals("user")&& edtPassword.getText().toString().trim().equals("123")){
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(USERNAME_KEY,edtUserName.getText().toString());
                    editor.putString(PASSWORD_KEY,edtPassword.getText().toString());

                    editor.apply();
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(LoginActivity.this,"Username or password incorrect",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (username != null && password != null) {
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
        }
    }
}
