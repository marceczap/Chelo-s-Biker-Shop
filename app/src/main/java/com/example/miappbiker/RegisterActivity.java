package com.example.miappbiker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {

    private ImageView btnBack;
    private EditText etEmail;
    private EditText etPassword;
    private AppCompatButton btnAcceder;
    private ImageView btnGoogle;
    private ImageView btnApple;
    private ImageView btnFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.register_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBack = findViewById(R.id.btn_back_to_login);
        etEmail = findViewById(R.id.et_reg_email);
        etPassword = findViewById(R.id.et_reg_password);
        btnAcceder = findViewById(R.id.btn_reg_acceder);
        btnGoogle = findViewById(R.id.btn_auth_google);
        btnApple = findViewById(R.id.btn_auth_apple);
        btnFacebook = findViewById(R.id.btn_auth_facebook);

        btnBack.setOnClickListener(v -> finish());

        btnAcceder.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();
            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Por favor completa tu correo y contraseña", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "¡Registro completado para: " + email + "!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnGoogle.setOnClickListener(v -> {
            Toast.makeText(this, "¡Conectado con Google! 🌐", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });
        btnApple.setOnClickListener(v -> {
            Toast.makeText(this, "¡Conectado con Apple ID! 🍏", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });
        btnFacebook.setOnClickListener(v -> {
            Toast.makeText(this, "¡Conectado con Facebook! 👤", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
