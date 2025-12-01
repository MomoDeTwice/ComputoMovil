package com.fic.cursoandroid2025g4.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fic.cursoandroid2025g4.R;
import com.fic.cursoandroid2025g4.model.Task;

import java.util.ArrayList;
import java.util.List;

public  class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private final List<Task> taskList = new ArrayList<>();
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onEditClick(Task task);
        void onDeleteClick(Task task);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setData(List<Task> tasks){
        taskList.clear();
        if(tasks != null){
            taskList.addAll(tasks);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task,parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.txtTitle.setText(task.taskTittle);
        holder.txtDescription.setText(task.taskDescription);
        holder.txtCreatedAt.setText(task.createdAt);
        holder.txtStatus.setText(task.isCompleted);

        holder.ivEdit.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEditClick(task);
            }
        });

        holder.ivDelete.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDeleteClick(task);
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }


    public static class TaskViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle, txtDescription, txtCreatedAt, txtStatus;
        ImageView ivEdit, ivDelete;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.tvTitle);
            txtDescription = itemView.findViewById(R.id.tvDescription);
            txtCreatedAt = itemView.findViewById(R.id.tvCreatedAt);
            txtStatus = itemView.findViewById(R.id.tvStatus);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivDelete = itemView.findViewById(R.id.ivDelete);
        }
    }
}
