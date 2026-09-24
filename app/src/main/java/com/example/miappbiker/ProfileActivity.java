package com.example.miappbiker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileActivity extends AppCompatActivity {

    private SessionManager sessionManager;

    private TextView tvDisplayName;
    private EditText etName;
    private EditText etEmail;
    private EditText etPhone;
    private EditText etCi;
    private EditText etCity;
    private EditText etMoto;
    private EditText etLicense;
    private EditText etEmergency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.profile_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sessionManager = new SessionManager(this);

        initViews();
        loadUserData();
        setupListeners();
    }

    private void initViews() {
        tvDisplayName = findViewById(R.id.tv_display_name);
        etName = findViewById(R.id.et_profile_name);
        etEmail = findViewById(R.id.et_profile_email);
        etPhone = findViewById(R.id.et_profile_phone);
        etCi = findViewById(R.id.et_profile_ci);
        etCity = findViewById(R.id.et_profile_city);
        etMoto = findViewById(R.id.et_profile_moto);
        etLicense = findViewById(R.id.et_profile_license);
        etEmergency = findViewById(R.id.et_profile_emergency);
    }

    private void loadUserData() {
        String name = sessionManager.getUserName();
        tvDisplayName.setText(name);
        etName.setText(name);
        etEmail.setText(sessionManager.getUserEmail());
        etPhone.setText(sessionManager.getUserPhone());
        etCi.setText(sessionManager.getUserCI());
        etCity.setText(sessionManager.getUserCity());
        etMoto.setText(sessionManager.getUserMoto());
        etLicense.setText(sessionManager.getUserLicense());
        etEmergency.setText(sessionManager.getUserEmergency());
    }

    private void setupListeners() {
        ImageView btnBack = findViewById(R.id.btn_profile_back);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        FrameLayout btnChangeAvatar = findViewById(R.id.btn_change_avatar);
        if (btnChangeAvatar != null) {
            btnChangeAvatar.setOnClickListener(v ->
                    Toast.makeText(this, "Selecciona una nueva foto de perfil 📷", Toast.LENGTH_SHORT).show()
            );
        }

        AppCompatButton btnSave = findViewById(R.id.btn_save_profile);
        if (btnSave != null) {
            btnSave.setOnClickListener(v -> saveUserData());
        }

        // Navigation
        FrameLayout btnHome = findViewById(R.id.nav_btn_home);
        if (btnHome != null) {
            btnHome.setOnClickListener(v -> {
                Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            });
        }

        FrameLayout btnMenu = findViewById(R.id.nav_btn_menu);
        if (btnMenu != null) {
            btnMenu.setOnClickListener(v -> HamburgerMenuBottomSheet.showMenu(getSupportFragmentManager()));
        }
    }

    private void saveUserData() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String ci = etCi.getText().toString().trim();
        String city = etCity.getText().toString().trim();
        String moto = etMoto.getText().toString().trim();
        String license = etLicense.getText().toString().trim();
        String emergency = etEmergency.getText().toString().trim();

        if (name.isEmpty()) name = "Marcelo Biker";
        if (email.isEmpty()) email = "marcelo@chelobiker.com";

        sessionManager.saveProfileDetails(name, email, phone, city, ci, moto, license, emergency);
        tvDisplayName.setText(name);

        Toast.makeText(this, "✅ Datos de perfil guardados correctamente", Toast.LENGTH_SHORT).show();
    }
}
