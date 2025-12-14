package com.fic.cursoandroid2025g4.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fic.cursoandroid2025g4.R;
import com.fic.cursoandroid2025g4.model.History;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private final List<History> historyList = new ArrayList<>();
    public void setData(List<History> history){
        historyList.clear();
        if (history != null){
            historyList.addAll(history);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int positon){
        History history = historyList.get(positon);

        String actionText = formatAction(history.action);
        holder.tvAction.setText(actionText);
        holder.tvDetails.setText(history.details);
        holder.tvCreatedAt.setText(history.createdAt);
    }

    @Override
    public int getItemCount(){
        return historyList.size();
    }

    private String formatAction(String action){
        if (action == null) return "Desconocido";
        switch (action) {
            case "insert_task":
                return "Tarea Creada";
            case "update_task":
                return "Tarea Actualizada";
            case "delete_task":
                return "Tarea Eliminada";
            default:
                return action;
        }
    }
    public static class HistoryViewHolder extends RecyclerView.ViewHolder{
        TextView tvAction, tvDetails, tvCreatedAt;

        public HistoryViewHolder(@NonNull View itemView){
            super(itemView);
            tvAction = itemView.findViewById(R.id.tvHistoryAction);
            tvDetails = itemView.findViewById(R.id.tvHistoryDetails);
            tvCreatedAt = itemView.findViewById(R.id.tvHistoryCreatedAt);
        }
    }

}
