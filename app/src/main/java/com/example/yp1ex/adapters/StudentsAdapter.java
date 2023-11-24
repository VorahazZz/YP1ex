package com.example.yp1ex.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yp1ex.R;
import com.example.yp1ex.data.Students;
import com.example.yp1ex.data_base.DataBaseManager;

import java.util.List;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.ViewHolder> {
    public interface OnStudentClickListener{
        void OnStudentClick(Students student, int position);
    }
    OnStudentClickListener onClickListener;
    List<Students> studentsList;
    LayoutInflater layoutInflater;
    DataBaseManager dataBaseManager;
    public StudentsAdapter(Context context, List<Students> studentsList, OnStudentClickListener onClickListener){
        this.layoutInflater = LayoutInflater.from(context);
        this.studentsList = studentsList;
        this.onClickListener = onClickListener;
    }
    @NonNull
    @Override
    public StudentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_student, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull StudentsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        dataBaseManager = new DataBaseManager(holder.itemView.getContext());
        dataBaseManager.openDb();
        Students student = studentsList.get(position);
        holder.tvFIO.setText(student.getSecondName() + " " + student.getFirstName() + " " + student.getSurname());
        holder.tvDate.setText(student.getDate());
        holder.tvGroup.setText(dataBaseManager.getGroup(student.getIdGroup()).getNumber() + " " + dataBaseManager.getGroup(student.getIdGroup()).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.OnStudentClick(student, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvFIO, tvDate, tvGroup;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFIO = itemView.findViewById(R.id.textViewStudentFIO);
            tvDate = itemView.findViewById(R.id.textViewStudentDate);
            tvGroup = itemView.findViewById(R.id.textViewStudentGroup);
        }
    }
}
