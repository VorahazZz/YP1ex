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
import com.example.yp1ex.data.Groups;

import java.util.List;

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.ViewHolder> {
    public interface OnGroupClickListener{
        void OnGroupClick(Groups group, int pos);
    }
    OnGroupClickListener onClickListener;
    LayoutInflater layoutInflater;
    List<Groups> groupsList;

    public GroupsAdapter(Context context, List<Groups> groupsList, OnGroupClickListener onClickListener){
        this.layoutInflater = LayoutInflater.from(context);
        this.onClickListener = onClickListener;
        this.groupsList = groupsList;
    }

    @NonNull
    @Override
    public GroupsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_group, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Groups group = groupsList.get(position);
        holder.tvNum.setText(group.getNumber());
        holder.tvName.setText(group.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.OnGroupClick(group, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return groupsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvNum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.textViewGroupName);
            tvNum = itemView.findViewById(R.id.textViewGroupNum);
        }
    }
}
