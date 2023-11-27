package com.example.yp1ex.fragments.groups;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yp1ex.R;
import com.example.yp1ex.data.Groups;
import com.example.yp1ex.data.Students;
import com.example.yp1ex.data_base.DataBaseManager;
import com.example.yp1ex.databinding.FragmentAddEditGroupBinding;

import java.util.List;
import java.util.function.ObjIntConsumer;

public class AddEditGroupFragment extends Fragment {
    FragmentAddEditGroupBinding binding;
    Groups group = new Groups();
    DataBaseManager dataBaseManager;
    public AddEditGroupFragment() {
        // Required empty public constructor
    }

    public AddEditGroupFragment(Groups group) {
        this.group = group;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataBaseManager = new DataBaseManager(getContext());
        dataBaseManager.openDb();
        if (group.getId() != 0){
            binding.editTextGroupName.setText(group.getName());
            binding.editTextGroupNum.setText(group.getNumber() + "");
            binding.buttonDelete.setVisibility(View.VISIBLE);
            binding.buttonGroupAddEdit.setText("Редактировать");
        }
        binding.buttonGroupAddEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                group.setNumber(Integer.parseInt(binding.editTextGroupNum.getText().toString()));
                group.setName(binding.editTextGroupName.getText().toString());
                if (group.getId() == 0){
                    //Groups group = new Groups(Integer.parseInt(binding.editTextGroupNum.getText().toString()), binding.editTextGroupName.getText().toString());
                    dataBaseManager.addGroups(group);
                    Toast.makeText(getContext(), "Группа добавлена", Toast.LENGTH_SHORT).show();
                }
                else {
                    dataBaseManager.updGroups(group);
                    Toast.makeText(getContext(), "Группа изменена", Toast.LENGTH_SHORT).show();
                }
                getParentFragmentManager().popBackStack();
            }
        });
        binding.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Students> studentsList = dataBaseManager.getStudents();
                int countStudents = 0;
                for (Students student : studentsList){
                    if (student.getIdGroup() == group.getId())
                        countStudents++;
                }
                if (countStudents > 0){
                    Toast.makeText(getContext(), "Группу нельзя удалить, потому что ней есть студенты!", Toast.LENGTH_SHORT).show();
                }
                else {
                    dataBaseManager.delGroup(group);
                    Toast.makeText(getContext(), "Группа удалена", Toast.LENGTH_SHORT).show();
                    getParentFragmentManager().popBackStack();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddEditGroupBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dataBaseManager.closeDb();
    }
}