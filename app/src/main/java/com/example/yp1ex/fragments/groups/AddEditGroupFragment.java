package com.example.yp1ex.fragments.groups;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yp1ex.R;
import com.example.yp1ex.databinding.FragmentAddEditGroupBinding;

import java.util.function.ObjIntConsumer;

public class AddEditGroupFragment extends Fragment {
    FragmentAddEditGroupBinding binding;
    int groupId;
    public AddEditGroupFragment() {
        // Required empty public constructor
    }

    public AddEditGroupFragment(int groupId) {
        this.groupId = groupId;
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
        binding = FragmentAddEditGroupBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }
}