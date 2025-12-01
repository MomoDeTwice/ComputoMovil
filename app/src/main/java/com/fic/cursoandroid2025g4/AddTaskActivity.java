package com.fic.cursoandroid2025g4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fic.cursoandroid2025g4.controller.TaskController;
import com.fic.cursoandroid2025g4.model.Task;

public class AddTaskActivity extends AppCompatActivity {

    private EditText etTaskTittle;
    private EditText etTaskDescription;
    private EditText etCreatedAt;
    private EditText etIsCompleted;
    private Button btnSave;
    
    private int taskId = -1; // -1 indica modo creación


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_task);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initViews();
        
        // Comprobar si venimos a editar
        checkForEditMode();

        btnSave.setOnClickListener(view -> {
            String taskTittle = etTaskTittle.getText().toString();
            String taskDescription = etTaskDescription.getText().toString();
            String createdAt = etCreatedAt.getText().toString();
            String isCompleted = etIsCompleted.getText().toString();
            
            if (taskTittle.isEmpty() || taskDescription.isEmpty() || createdAt.isEmpty() || isCompleted.isEmpty()) {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                if (taskId != -1) {
                    updateTask(taskTittle, taskDescription, createdAt, isCompleted);
                } else {
                    saveTask(taskTittle, taskDescription, createdAt, isCompleted);
                }
            }
        });

    }
    
    private void checkForEditMode() {
        Intent intent = getIntent();
        if (intent.hasExtra("TASK_ID")) {
            taskId = intent.getIntExtra("TASK_ID", -1);
            etTaskTittle.setText(intent.getStringExtra("TASK_TITLE"));
            etTaskDescription.setText(intent.getStringExtra("TASK_DESC"));
            etCreatedAt.setText(intent.getStringExtra("TASK_DATE"));
            etIsCompleted.setText(intent.getStringExtra("TASK_STATUS"));
            btnSave.setText("Actualizar");
        }
    }

    private void saveTask(String taskTittle, String taskDescription, String createdAt, String isCompleted){
        TaskController taskController = new TaskController(this);
        boolean result = taskController.addTask(taskTittle,taskDescription,createdAt,isCompleted);

        if(result){
            Toast.makeText(this, getString(R.string.task_saved_success), Toast.LENGTH_SHORT).show();
            clearForm();
            showTaskActivity();
        }else{
            Toast.makeText(this, getString(R.string.error_task_save), Toast.LENGTH_SHORT).show();
        }
    }
    
    private void updateTask(String taskTittle, String taskDescription, String createdAt, String isCompleted) {
        TaskController taskController = new TaskController(this);
        Task task = new Task();
        task.id = taskId;
        task.taskTittle = taskTittle;
        task.taskDescription = taskDescription;
        task.createdAt = createdAt;
        task.isCompleted = isCompleted;
        
        try {
            taskController.updateTask(task);
            Toast.makeText(this, "Tarea actualizada", Toast.LENGTH_SHORT).show();
            clearForm();
            showTaskActivity();
        } catch (Exception e) {
            Toast.makeText(this, "Error al actualizar", Toast.LENGTH_SHORT).show();
        }
    }

    private void showTaskActivity(){
        finish();
    }

    private void clearForm(){
        etTaskTittle.setText("");
        etTaskDescription.setText("");
        etCreatedAt.setText("");
        etIsCompleted.setText("");
    }

    private void initViews(){
        etTaskTittle = findViewById(R.id.etTaskTittle);
        etTaskDescription = findViewById(R.id.etTaskDescription);
        etCreatedAt = findViewById(R.id.etCreatedAt);
        etIsCompleted = findViewById(R.id.etIsCompleted);
        btnSave = findViewById(R.id.btnSave);
    }
}
