package com.example.yp1ex.fragments.groups;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yp1ex.R;
import com.example.yp1ex.adapters.GroupsAdapter;
import com.example.yp1ex.data.Groups;
import com.example.yp1ex.data_base.DataBaseManager;
import com.example.yp1ex.databinding.FragmentGroupsBinding;
import com.example.yp1ex.fragments.students.AddEditStudentFragment;

import java.util.List;


public class GroupsFragment extends Fragment {
    FragmentGroupsBinding binding;
    DataBaseManager dataBaseManager;
    public GroupsFragment() {
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
        List<Groups> groupsList = dataBaseManager.getGroups();
        GroupsAdapter.OnGroupClickListener onGroupClickListener = new GroupsAdapter.OnGroupClickListener() {
            @Override
            public void OnGroupClick(Groups group, int pos) {
                AddEditGroupFragment addEditGroupFragment = new AddEditGroupFragment(group);
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, addEditGroupFragment, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("groupUpd")
                        .commit();
            }
        };
        GroupsAdapter groupsAdapter = new GroupsAdapter(getContext(), groupsList, onGroupClickListener);
        binding.recyclerViewGroups.setAdapter(groupsAdapter);
        binding.buttonAddGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, AddEditGroupFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("groupAdd")
                        .commit();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGroupsBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }
}