package com.example.miappbiker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private androidx.appcompat.widget.AppCompatButton btnLogin;
    private LinearLayout llRegisterLink;
    private ImageView btnSocialFb;
    private ImageView btnSocialIg;
    private ImageView btnSocialYt;

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sessionManager = new SessionManager(this);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login_arrow);
        llRegisterLink = findViewById(R.id.ll_register_link);
        btnSocialFb = findViewById(R.id.btn_social_fb);
        btnSocialIg = findViewById(R.id.btn_social_ig);
        btnSocialYt = findViewById(R.id.btn_social_yt);

        setupListeners();
    }

    private void setupListeners() {
        if (btnLogin != null) {
            btnLogin.setOnClickListener(v -> handleLogin());
        }

        etPassword.setOnEditorActionListener((v, actionId, event) -> {
            handleLogin();
            return true;
        });

        llRegisterLink.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        android.widget.TextView tvReg = findViewById(R.id.tv_register_btn);
        if (tvReg != null) {
            tvReg.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            });
        }

        btnSocialFb.setOnClickListener(v -> openUrl("https://www.facebook.com"));
        btnSocialIg.setOnClickListener(v -> openUrl("https://www.instagram.com"));
        btnSocialYt.setOnClickListener(v -> openUrl("https://www.youtube.com"));
    }

    private void handleLogin() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty()) {
            email = "marcelo@chelobiker.com";
        }
        if (password.isEmpty()) {
            password = "demo";
        }

        sessionManager.createLoginSession("Marcelo Biker", email, "+591 76543210", "Cochabamba", "8472910");
        Toast.makeText(this, "¡Bienvenido a Chelo's Biker Shop! 🏍️", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    private void showRegisterDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Registro de Usuario 🏍️")
                .setMessage("Ingresa tu correo y contraseña para crear tu cuenta en Chelo's Biker Shop.")
                .setPositiveButton("Crear Cuenta", (dialog, which) -> {
                    Toast.makeText(this, "¡Cuenta creada exitosamente! Ya puedes iniciar sesión.", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void openUrl(String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "No se pudo abrir el enlace: " + url, Toast.LENGTH_SHORT).show();
        }
    }
}