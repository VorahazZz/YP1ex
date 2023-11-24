package com.example.yp1ex.fragments.students;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yp1ex.R;
import com.example.yp1ex.databinding.FragmentAddEditStudentBinding;

public class AddEditStudentFragment extends Fragment {
    FragmentAddEditStudentBinding binding;
    int studentId;
    public AddEditStudentFragment() {
        // Required empty public constructor
    }

    public AddEditStudentFragment(int studentId){
        this.studentId = studentId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddEditStudentBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }
}