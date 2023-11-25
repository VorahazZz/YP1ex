package com.example.yp1ex.fragments.students;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yp1ex.R;
import com.example.yp1ex.adapters.StudentGroupAdapter;
import com.example.yp1ex.data.Groups;
import com.example.yp1ex.data.Students;
import com.example.yp1ex.data_base.DataBaseManager;
import com.example.yp1ex.databinding.FragmentAddEditStudentBinding;

import java.util.ArrayList;
import java.util.List;

public class AddEditStudentFragment extends Fragment {
    FragmentAddEditStudentBinding binding;
    //int studentId;
    Groups selGroup = new Groups();
    DataBaseManager dataBaseManager;
    List<Groups> groupsList = new ArrayList<>();
    Students student = new Students();
    public AddEditStudentFragment() {
        // Required empty public constructor
    }

    public AddEditStudentFragment(Students student){
        this.student = student;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(student.getId() != 0){
            binding.buttonStudentDelete.setVisibility(View.VISIBLE);
            binding.buttonStudentAddEdit.setText("Редактирвать");
            binding.editTextStudentSecName.setText(student.getSecondName());
            binding.editTextStudentFirName.setText(student.getFirstName());
            binding.editTextStudentSurName.setText(student.getSurname());
            binding.editTextStudentDate.setText(student.getDate());
        }
        dataBaseManager = new DataBaseManager(getContext());
        dataBaseManager.openDb();
        groupsList = dataBaseManager.getGroups();
        StudentGroupAdapter.OnGroupStdClickListener onGroupStdClickListener = new StudentGroupAdapter.OnGroupStdClickListener() {
            @Override
            public void OnGroupStudClick(Groups group, int pos) {
                selGroup = group;
            }
        };
        StudentGroupAdapter studentGroupAdapter = new StudentGroupAdapter(getContext(), groupsList, onGroupStdClickListener, student);
        binding.recyclerViewStudentsGroups.setAdapter(studentGroupAdapter);
        binding.buttonStudentAddEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selGroup.getId() == 0){
                    Toast.makeText(getContext(), "Выберите группу", Toast.LENGTH_SHORT).show();
                }
                else {
                    student.setDate(binding.editTextStudentDate.getText().toString());
                    student.setIdGroup(selGroup.getId());
                    student.setFirstName(binding.editTextStudentFirName.getText().toString());
                    student.setSecondName(binding.editTextStudentSecName.getText().toString());
                    student.setSurname(binding.editTextStudentSurName.getText().toString());
                    if (student.getId() == 0) {
                        dataBaseManager.addStudent(student);
                    } else {
                        dataBaseManager.updStudent(student);
                    }
                }
            }
        });
        binding.buttonStudentDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBaseManager.delStudents(student);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddEditStudentBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dataBaseManager.closeDb();
    }
}