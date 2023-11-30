package com.example.yp1ex.fragments.students;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yp1ex.R;
import com.example.yp1ex.adapters.StudentGroupAdapter;
import com.example.yp1ex.data.Groups;
import com.example.yp1ex.data.Students;
import com.example.yp1ex.data_base.DataBaseManager;
import com.example.yp1ex.databinding.FragmentSelectGroupBinding;

import java.util.ArrayList;
import java.util.List;

public class SelectGroupFragment extends Fragment {
    FragmentSelectGroupBinding binding;
    Students student = new Students();
    DataBaseManager dataBaseManager;
    List<Groups> groupsList = new ArrayList<>();
    public SelectGroupFragment() {
        // Required empty public constructor
    }
    public SelectGroupFragment(Students student){
        this.student = student;
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
        groupsList = dataBaseManager.getGroups();
        Bundle result = new Bundle();

        StudentGroupAdapter.OnGroupStdClickListener onGroupStdClickListener = new StudentGroupAdapter.OnGroupStdClickListener() {
            @Override
            public void OnGroupStudClick(Groups group, int pos) {
                student.setIdGroup(group.getId());
                result.putInt("groupId", group.getId());
                result.putString("groupName", group.getName());
                result.putInt("groupNum", group.getNumber());
                getParentFragmentManager().setFragmentResult("groupIdKey", result);
                Fragment fragment = getParentFragmentManager().findFragmentByTag("studentAddEdit");
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, fragment, null)
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        .commit();
            }
        };
        StudentGroupAdapter studentGroupAdapter = new StudentGroupAdapter(getContext(), groupsList, onGroupStdClickListener, student);
        binding.recyclerViewStudGroup.setAdapter(studentGroupAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSelectGroupBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dataBaseManager.closeDb();
    }
}