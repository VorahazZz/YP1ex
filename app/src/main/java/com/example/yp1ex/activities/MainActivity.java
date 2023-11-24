package com.example.yp1ex.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.yp1ex.R;
import com.example.yp1ex.data.Groups;
import com.example.yp1ex.data.Students;
import com.example.yp1ex.data_base.DataBaseManager;
import com.example.yp1ex.databinding.ActivityMainBinding;
import com.example.yp1ex.fragments.groups.GroupsFragment;
import com.example.yp1ex.fragments.students.StudentsFragment;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    DataBaseManager dataBaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setData();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, StudentsFragment.class, null)
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit();
        binding.bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.studentsMenu){
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainerView, StudentsFragment.class, null)
                            .setReorderingAllowed(true)
                            .addToBackStack(null)
                            .commit();
                }
                if (item.getItemId() == R.id.groupsMenu){
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainerView, GroupsFragment.class, null)
                            .setReorderingAllowed(true)
                            .addToBackStack(null)
                            .commit();
                }
                return true;
            }
        });
    }

    public void setData(){
        dataBaseManager = new DataBaseManager(MainActivity.this);
        dataBaseManager.openDb();
        dataBaseManager.addGroups(new Groups(1,"ИСП"));
        dataBaseManager.addGroups(new Groups(2,"ИСП"));
        dataBaseManager.addStudent(new Students("Захаров", "Александр", "Николаевич", "07.03.2004", 1));
        dataBaseManager.addStudent(new Students("Варламов", "Максим", "Игоревич", "30.05.2004", 1));
        dataBaseManager.addStudent(new Students("Головин", "Александр", "Григорьевич", "02.06.2004", 1));
        dataBaseManager.addStudent(new Students("Калужин", "Михаил", "Эдуардович", "01.02.2004", 2));
        dataBaseManager.addStudent(new Students("Путрин", "Владимир", "Владимирович", "15.11.2003", 2));
        dataBaseManager.addStudent(new Students("Пушкин", "Александр", "Сергеевич", "07.03.2004", 1));
        dataBaseManager.addStudent(new Students("Григорьев", "Владимир", "Сергеевич", "02.03.2004", 1));
        dataBaseManager.addStudent(new Students("Иванов", "Иван", "Иванович", "10.03.2003", 2));
        dataBaseManager.addStudent(new Students("Петров", "Петр", "Петрович", "20.03.2003", 2));
        dataBaseManager.addStudent(new Students("Минюшин", "Тихон", "Валерьевич", "17.04.2004", 1));
        dataBaseManager.addStudent(new Students("Козырев", "Александр", "Сергеевич", "11.08.2004", 1));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataBaseManager.closeDb();
    }
}