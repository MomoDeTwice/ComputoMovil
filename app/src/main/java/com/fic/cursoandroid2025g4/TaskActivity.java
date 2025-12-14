package com.fic.cursoandroid2025g4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fic.cursoandroid2025g4.controller.TaskController;
import com.fic.cursoandroid2025g4.model.Task;
import com.fic.cursoandroid2025g4.view.HistoryActivity;
import com.fic.cursoandroid2025g4.view.TaskAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TaskActivity extends AppCompatActivity {

    private TaskAdapter taskAdapter;
    private TaskController taskController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_task);

        // Configuración para Edge-to-Edge (Márgenes del sistema)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerViewTask = findViewById(R.id.rvTask);
        recyclerViewTask.setLayoutManager(new LinearLayoutManager(this));

        taskAdapter = new TaskAdapter();
        recyclerViewTask.setAdapter(taskAdapter);

        taskController = new TaskController(this);
        
        // Configurar listener para clicks en items
        taskAdapter.setOnItemClickListener(new TaskAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(Task task) {
                showEditTaskActivity(task);
            }

            @Override
            public void onDeleteClick(Task task) {
                showDeleteConfirmation(task);
            }
        });

        loadTask();

        // Corregido: Se usa R.id.fabAddBook en lugar de fabAddTask para coincidir con el XML
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) 
        FloatingActionButton fabAddBook = findViewById(R.id.fabAddBook);
        fabAddBook.setOnClickListener(view -> showAddTaskActivity());

        // Botón para ir al Historial
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        FloatingActionButton fabHistory = findViewById(R.id.fabHistory);
        fabHistory.setOnClickListener(view -> showHistoryActivity());
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Recargar la lista al volver a la actividad
        loadTask();
    }

    private void loadTask() {
        if (taskController != null && taskAdapter != null) {
            List<Task> task = taskController.getAllTasks();
            taskAdapter.setData(task);
        }
    }

    private void showAddTaskActivity(){
        Intent intent = new Intent(TaskActivity.this, AddTaskActivity.class);
        startActivity(intent);
    }
    
    private void showEditTaskActivity(Task task){
        Intent intent = new Intent(TaskActivity.this, AddTaskActivity.class);
        intent.putExtra("TASK_ID", task.id);
        intent.putExtra("TASK_TITLE", task.taskTittle);
        intent.putExtra("TASK_DESC", task.taskDescription);
        intent.putExtra("TASK_DATE", task.createdAt);
        intent.putExtra("TASK_STATUS", task.isCompleted);
        startActivity(intent);
    }
    
    private void showDeleteConfirmation(Task task) {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar Tarea")
                .setMessage("¿Estás seguro de que deseas eliminar esta tarea?")
                .setPositiveButton("Eliminar", (dialog, which) -> {
                    taskController.deleteTask(task);
                    Toast.makeText(this, "Tarea eliminada", Toast.LENGTH_SHORT).show();
                    loadTask();
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void showHistoryActivity() {
        Intent intent = new Intent(TaskActivity.this, HistoryActivity.class);
        startActivity(intent);
    }
}
