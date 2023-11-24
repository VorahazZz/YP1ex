package com.example.yp1ex.fragments.students;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.helper.widget.Carousel;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.yp1ex.R;
import com.example.yp1ex.adapters.StudentsAdapter;
import com.example.yp1ex.data.Students;
import com.example.yp1ex.data_base.DataBaseManager;
import com.example.yp1ex.databinding.FragmentStudentsBinding;

import java.util.Comparator;
import java.util.List;

public class StudentsFragment extends Fragment {
    FragmentStudentsBinding binding;
    DataBaseManager dataBaseManager;

    public StudentsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataBaseManager = new DataBaseManager(getContext());
        dataBaseManager.openDb();
        List<Students> studentsList = dataBaseManager.getStudents();
        StudentsAdapter.OnStudentClickListener onStudentClickListener = new StudentsAdapter.OnStudentClickListener() {
            @Override
            public void OnStudentClick(Students student, int position) {
                AddEditStudentFragment addEditStudentFragment = new AddEditStudentFragment(student.getId());
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, addEditStudentFragment, null)
                        .addToBackStack(null)
                        .setReorderingAllowed(true)
                        .commit();
            }
        };
        StudentsAdapter studentsAdapter = new StudentsAdapter(getContext(), studentsList, onStudentClickListener);
        binding.recyclerViewStudents.setAdapter(studentsAdapter);
        binding.buttonAddStud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, AddEditStudentFragment.class, null)
                        .addToBackStack(null)
                        .setReorderingAllowed(true)
                        .commit();
            }
        });
        binding.checkBoxSort.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    studentsList.sort(new Comparator<Students>() {
                        @Override
                        public int compare(Students o1, Students o2) {
                            return Integer.compare(dataBaseManager.getGroup(o1.getIdGroup()).getNumber(), dataBaseManager.getGroup(o2.getIdGroup()).getNumber());
                        }
                    }.thenComparing(new Comparator<Students>() {
                        @Override
                        public int compare(Students o1, Students o2) {
                            return String.CASE_INSENSITIVE_ORDER.compare(o1.getSecondName(), o2.getSecondName());
                        }
                    }));
                } else {
                    studentsList.sort(new Comparator<Students>() {
                        @Override
                        public int compare(Students o1, Students o2) {
                            return String.CASE_INSENSITIVE_ORDER.compare(o1.getSecondName(), o2.getSecondName());
                        }
                    });
                }
                StudentsAdapter studentsAdapter = new StudentsAdapter(getContext(), studentsList, onStudentClickListener);
                binding.recyclerViewStudents.setAdapter(studentsAdapter);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStudentsBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dataBaseManager.closeDb();
    }
}