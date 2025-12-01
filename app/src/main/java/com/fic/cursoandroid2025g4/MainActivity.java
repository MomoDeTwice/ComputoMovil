package com.fic.cursoandroid2025g4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout tilUsername, tilPassword, tilEmail;
    private TextInputEditText etUsername, etPassword, etEmail;
    private Button btnSendData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupClickListener();

    }

    private void initViews(){
        tilUsername = findViewById(R.id.tilUsername);
        tilPassword = findViewById(R.id.tilPassword);
        tilEmail = findViewById(R.id.tilEmail);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail);
        btnSendData = findViewById(R.id.btnSendData);
    }

    private void setupClickListener(){
        btnSendData.setOnClickListener(view -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String email = etEmail.getText().toString().trim();

            if(validateForm(username,password,email)){
                Toast.makeText(this, "Bienvenido " + username, Toast.LENGTH_SHORT).show();
                // Redireccionar a TaskActivity (Listado de tareas)
                Intent intent = new Intent(MainActivity.this, TaskActivity.class);
                startActivity(intent);
                finish(); // Opcional: cerrar MainActivity para que no se pueda volver atrás al login
            }
        });
    }

    private boolean validateForm(String username, String password, String email){
        boolean isValid = true;

        //Validar nombre de usuario
        if(username.isEmpty()){
            tilUsername.setError(getString(R.string.activity_main_username_validate));
            isValid = false;
        }else{
            tilUsername.setError(null);
        }

        //Validar correo electrónico
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            tilEmail.setError(getString(R.string.activity_main_email_validate));
            isValid = false;
        }else{
            tilEmail.setError(null);
        }

        if(password.length() < 8){
            tilPassword.setError(getString(R.string.activity_main_password_validate));
            isValid = false;
        }else{
            tilPassword.setError(null);
        }



        return isValid;
    }
}
