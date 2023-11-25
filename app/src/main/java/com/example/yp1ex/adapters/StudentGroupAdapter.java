package com.example.yp1ex.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yp1ex.R;
import com.example.yp1ex.data.Groups;
import com.example.yp1ex.data.Students;

import java.util.List;

public class StudentGroupAdapter extends RecyclerView.Adapter<StudentGroupAdapter.ViewHolder> {
    public interface OnGroupStdClickListener {
        void OnGroupStudClick(Groups group, int pos);
    }

    LayoutInflater layoutInflater;
    List<Groups> groupsList;
    OnGroupStdClickListener onClickListener;
    Students student;
    private boolean isNewRBChecked = false;
    private int lastCheckedPos = -1;

    public StudentGroupAdapter(Context context, List<Groups> groupsList, OnGroupStdClickListener onClickListener, Students student) {
        this.layoutInflater = LayoutInflater.from(context);
        this.groupsList = groupsList;
        this.onClickListener = onClickListener;
        this.student = student;
    }

    @NonNull
    @Override
    public StudentGroupAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_student_group, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentGroupAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Groups group = groupsList.get(position);
        holder.tvNum.setText(String.valueOf(group.getNumber()));
        holder.tvName.setText(group.getName());
        if (isNewRBChecked) {
            holder.radioButton.setChecked(group.isSelected());
        } else if (student.getIdGroup() == group.getId()) {
            holder.radioButton.setChecked(true);
            lastCheckedPos = holder.getAdapterPosition();
        } else if (holder.getAdapterPosition() == 0) {
            holder.radioButton.setChecked(false);
            lastCheckedPos = 0;
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleRBChecks(holder.getAdapterPosition());
                onClickListener.OnGroupStudClick(group, position);
            }
        });
        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleRBChecks(holder.getAdapterPosition());
                onClickListener.OnGroupStudClick(group, position);
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void handleRBChecks(int adapterPosition) {
        isNewRBChecked = true;
        groupsList.get(lastCheckedPos).setSelected(false);
        groupsList.get(adapterPosition).setSelected(true);
        lastCheckedPos = adapterPosition;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return groupsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNum, tvName;
        RadioButton radioButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNum = itemView.findViewById(R.id.textViewStudentGroupNum);
            tvName = itemView.findViewById(R.id.textViewStudentGroupName);
            radioButton = itemView.findViewById(R.id.rbStudGrCheck);

//            radioButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    handleRBChecks(getAdapterPosition());
//                }
//            });
        }
    }
}
