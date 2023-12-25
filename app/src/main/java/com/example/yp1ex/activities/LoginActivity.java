package com.example.yp1ex.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.yp1ex.R;
import com.example.yp1ex.data.Users;
import com.example.yp1ex.data_base.DataBaseManager;
import com.example.yp1ex.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    DataBaseManager dataBaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dataBaseManager = new DataBaseManager(this);
        dataBaseManager.openDb();
        binding.buttonLogEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = binding.editTextLogLogin.getText().toString();
                String pass = binding.editTextLogPassword.getText().toString();
                if (login.equals("") && pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Все поля должны быть заполнены!", Toast.LENGTH_SHORT).show();
                else{
                    Users user = dataBaseManager.getUser(login, pass);
                    if (user.getId() != 0) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else Toast.makeText(LoginActivity.this, "Вы ввели неверный логин или пароль!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.buttonLogReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataBaseManager.closeDb();
    }
}