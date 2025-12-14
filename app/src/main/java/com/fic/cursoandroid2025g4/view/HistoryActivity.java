package com.fic.cursoandroid2025g4.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fic.cursoandroid2025g4.R;
import com.fic.cursoandroid2025g4.controller.HistoryController;
import com.fic.cursoandroid2025g4.model.History;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerViewHistory;
    private HistoryAdapter historyAdapter;
    private HistoryController historyController;
    private Spinner spinnerActionFilter;
    private EditText etDateFilter;
    private Button btnSearch, btnClearFilters;
    private TextView tvEmptyHistory;
    
    private String selectedAction = "all";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.historyMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initViews();
        setupRecyclerView();
        setupFilters();
        loadHistory();
    }
    
    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    private void initViews(){
        recyclerViewHistory = findViewById(R.id.RVhistory);
        spinnerActionFilter = findViewById(R.id.spinnerActionFilter);
        etDateFilter = findViewById(R.id.etDateFilter);
        btnSearch = findViewById(R.id.btnSearch);
        btnClearFilters = findViewById(R.id.btnClearFilters);
        tvEmptyHistory = findViewById(R.id.tvEmptyHistory);
        historyController = new HistoryController(this);
    }

    private void setupRecyclerView(){
        recyclerViewHistory.setLayoutManager(new LinearLayoutManager(this));
        historyAdapter = new HistoryAdapter();
        recyclerViewHistory.setAdapter(historyAdapter);
    }

    private void setupFilters() {

        String[] actions = {"Todas las acciones", "Tarea Creada", "Tarea Actualizada", "Tarea Eliminada"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, actions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerActionFilter.setAdapter(adapter);

        spinnerActionFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        selectedAction = "all";
                        break;
                    case 1:
                        selectedAction = "insert_task";
                        break;
                    case 2:
                        selectedAction = "update_task";
                        break;
                    case 3:
                        selectedAction = "delete_task";
                        break;
                }
                // Aplicar búsqueda automáticamente al cambiar el filtro de acción
                applySearch();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        
        btnSearch.setOnClickListener(v -> applySearch());
        
        btnClearFilters.setOnClickListener(v -> clearFilters());

    }


    private void clearFilters() {
        etDateFilter.setText("");
        spinnerActionFilter.setSelection(0);
        // selectedAction se actualizará en el listener del spinner, que a su vez llamará a applySearch
        // pero para asegurar consistencia inmediata si el listener tarda o no dispara por ser la misma posición:
        selectedAction = "all";
        loadHistory();
    }

    private void applySearch() {
        String dateQuery = etDateFilter.getText().toString().trim();
        List<History> filteredHistory;

        // Lógica de filtrado
        if (!dateQuery.isEmpty()) {
            if (!selectedAction.equals("all")) {
                filteredHistory = historyController.getHistoryByActionAndDate(selectedAction, dateQuery);
            } else {
                filteredHistory = historyController.getHistoryByDate(dateQuery);
            }
        } else {
             if (!selectedAction.equals("all")) {
                filteredHistory = historyController.getHistoryByAction(selectedAction);
            } else {
                filteredHistory = historyController.getAllHistory();
            }
        }

        updateUI(filteredHistory);
    }

    private void loadHistory() {
        List<History> history = historyController.getAllHistory();
        updateUI(history);
    }

    private void updateUI(List<History> history) {
        if (history != null && !history.isEmpty()) {
            historyAdapter.setData(history);
            tvEmptyHistory.setVisibility(View.GONE);
            recyclerViewHistory.setVisibility(View.VISIBLE);
        } else {
            historyAdapter.setData(null);
            tvEmptyHistory.setVisibility(View.VISIBLE);
            recyclerViewHistory.setVisibility(View.GONE);
            // Opcional: Mostrar Toast si es una búsqueda explícita que no dio resultados
            // Toast.makeText(this, "No hay registros", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        // Recargar historial al volver a la pantalla
        // Si hay filtros puestos, quizás queramos mantenerlos.
        // Por simplicidad, recargamos la búsqueda actual.
        applySearch();
    }

}