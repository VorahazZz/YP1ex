package com.example.yp1ex.fragments.students;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.yp1ex.R;
import com.example.yp1ex.adapters.StudentGroupAdapter;
import com.example.yp1ex.data.Groups;
import com.example.yp1ex.data.Students;
import com.example.yp1ex.data_base.DataBaseManager;
import com.example.yp1ex.databinding.FragmentAddEditStudentBinding;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddEditStudentFragment extends Fragment {
    FragmentAddEditStudentBinding binding;
    DataBaseManager dataBaseManager;
    List<Groups> groupsList = new ArrayList<>();
    Students student = new Students();
    Groups group = new Groups();
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
        setInitialDateTime();
        getParentFragmentManager().setFragmentResultListener("groupIdKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                group.setId(result.getInt("groupId"));
                group.setName(result.getString("groupName"));
                group.setNumber(result.getInt("groupNum"));
                binding.textViewStudentGroupSet.setText(group.getName() + " " + group.getNumber());
            }
        });
        if(student.getId() != 0){
            binding.buttonStudentDelete.setVisibility(View.VISIBLE);
            binding.buttonStudentAddEdit.setText("Редактирвать");
            binding.editTextStudentSecName.setText(student.getSecondName());
            binding.editTextStudentFirName.setText(student.getFirstName());
            binding.editTextStudentSurName.setText(student.getSurname());
            binding.textViewStudentDate.setText(student.getDate());
        }
        dataBaseManager = new DataBaseManager(getContext());
        dataBaseManager.openDb();
        binding.buttonStudentAddEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (group.getId() == 0){
                    Toast.makeText(getContext(), "Вы должны выбрать группу", Toast.LENGTH_SHORT).show();
                }
                else {
                    student.setIdGroup(group.getId());
                    student.setDate(binding.textViewStudentDate.getText().toString());
                    student.setFirstName(binding.editTextStudentFirName.getText().toString());
                    student.setSecondName(binding.editTextStudentSecName.getText().toString());
                    student.setSurname(binding.editTextStudentSurName.getText().toString());
                    if (student.getId() == 0) {
                        dataBaseManager.addStudent(student);
                        Toast.makeText(getContext(), "Студент добавлен", Toast.LENGTH_SHORT).show();
                    } else {
                        dataBaseManager.updStudent(student);
                        Toast.makeText(getContext(), "Студент изменен", Toast.LENGTH_SHORT).show();
                    }
                    getParentFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainerView, StudentsFragment.class, null)
                            .setReorderingAllowed(true)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
        binding.buttonStudentDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBaseManager.delStudents(student);
                Toast.makeText(getContext(), "Студент удален", Toast.LENGTH_SHORT).show();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, StudentsFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        .commit();
            }
        });
        binding.textViewStudentGroupSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectGroupFragment selectGroupFragment = new SelectGroupFragment(student);
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, selectGroupFragment,  null)
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        .commit();
            }
        });
        binding.textViewStudentDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(), d,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH))
                        .show();
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
    Calendar dateAndTime=Calendar.getInstance();

    private void setInitialDateTime() {

        binding.textViewStudentDate.setText(DateUtils.formatDateTime(getContext(),
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_SHOW_YEAR));
    }
    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };
}