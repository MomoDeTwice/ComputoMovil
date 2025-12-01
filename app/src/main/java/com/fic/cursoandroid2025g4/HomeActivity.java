package com.fic.cursoandroid2025g4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fic.cursoandroid2025g4.controller.TaskController;
import com.fic.cursoandroid2025g4.model.Task;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Es recomendable llamar a super primero
        setContentView(R.layout.activity_home);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre_usuario");
        TextView txtNombre = findViewById(R.id.txtMensaje);
        // Usar un string por defecto si nombre es null para evitar errores visuales
        if (nombre != null) {
             String mensaje = getString(R.string.saludo_usuario, nombre);
             txtNombre.setText(mensaje);
        }

        Button btnGetTaskNames = findViewById(R.id.btnGetTaskNames);
        btnGetTaskNames.setOnClickListener(v -> showAllTaskNames());
    }

    private void showAllTaskNames() {
        TaskController taskController = new TaskController(this);
        List<Task> tasks = taskController.getAllTasks();

        if (tasks.isEmpty()) {
            Toast.makeText(this, "No hay tareas registradas", Toast.LENGTH_SHORT).show();
        } else {
            StringBuilder names = new StringBuilder();
            for (Task task : tasks) {
                names.append(task.taskTittle).append("\n");
            }
            // Mostrar los nombres en un Toast largo o podrías usar un Dialog
            Toast.makeText(this, names.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
