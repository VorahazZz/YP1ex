package com.example.yp1ex.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.yp1ex.R;
import com.example.yp1ex.data.Users;
import com.example.yp1ex.data_base.DataBaseManager;
import com.example.yp1ex.databinding.ActivityRegBinding;

public class RegActivity extends AppCompatActivity {
    ActivityRegBinding binding;
    DataBaseManager dataBaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        binding = ActivityRegBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dataBaseManager = new DataBaseManager(this);
        dataBaseManager.openDb();
        binding.buttonRegEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = binding.editTextRegLogin.getText().toString();
                String pass = binding.editTextRegPassword.getText().toString();
                String phone = binding.editTextRegPhone.getText().toString();

                if (login.equals("") && pass.equals("") && phone.equals(""))
                    Toast.makeText(RegActivity.this, "Все поля должны быть заполнены!", Toast.LENGTH_SHORT).show();
                else{
                    Users user = new Users();
                    user.setLogin(login);
                    user.setPass(pass);
                    user.setPhone(phone);
                    dataBaseManager.addUser(user);
                    Toast.makeText(RegActivity.this, "Вы успешно зарегистрировались!\nТеперь нужно войти",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataBaseManager.closeDb();
    }
}