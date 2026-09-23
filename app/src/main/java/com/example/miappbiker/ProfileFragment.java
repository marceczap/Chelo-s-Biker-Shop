package com.example.miappbiker;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    private TextView tvProfileName;
    private TextView tvProfileEmail;
    private TextView tvProfilePhone;
    private TextView tvProfileCity;
    private AppCompatButton btnEditProfile;
    private AppCompatButton btnAuthToggle;

    private SessionManager sessionManager;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        sessionManager = new SessionManager(requireContext());

        tvProfileName = view.findViewById(R.id.tv_profile_name);
        tvProfileEmail = view.findViewById(R.id.tv_profile_email);
        tvProfilePhone = view.findViewById(R.id.tv_profile_phone);
        tvProfileCity = view.findViewById(R.id.tv_profile_city);
        btnEditProfile = view.findViewById(R.id.btn_edit_profile);
        btnAuthToggle = view.findViewById(R.id.btn_auth_toggle);

        loadUserData();

        btnEditProfile.setOnClickListener(v -> showEditLoginDialog());

        btnAuthToggle.setOnClickListener(v -> {
            if (sessionManager.isLoggedIn()) {
                sessionManager.logout();
                Toast.makeText(getContext(), "Sesión cerrada", Toast.LENGTH_SHORT).show();
                loadUserData();
            } else {
                showEditLoginDialog();
            }
        });

        return view;
    }

    private void loadUserData() {
        if (sessionManager.isLoggedIn()) {
            tvProfileName.setText(sessionManager.getUserName());
            tvProfileEmail.setText(sessionManager.getUserEmail());
            tvProfilePhone.setText("Tel: " + sessionManager.getUserPhone());
            tvProfileCity.setText("Ciudad: " + sessionManager.getUserCity());
            btnAuthToggle.setText("Cerrar Sesión");
            btnAuthToggle.setTextColor(Color.parseColor("#D32F2F"));
        } else {
            tvProfileName.setText("Invitado / Biker");
            tvProfileEmail.setText("No has iniciado sesión");
            tvProfilePhone.setText("Tel: Sin registrar");
            tvProfileCity.setText("Ciudad: Cochabamba, BO");
            btnAuthToggle.setText("Iniciar Sesión / Registro");
            btnAuthToggle.setTextColor(Color.parseColor("#2563EB"));
        }
    }

    private void showEditLoginDialog() {
        Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_login);

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        EditText etName = dialog.findViewById(R.id.et_login_name);
        EditText etEmail = dialog.findViewById(R.id.et_login_email);
        EditText etPhone = dialog.findViewById(R.id.et_login_phone);
        EditText etCity = dialog.findViewById(R.id.et_login_city);
        AppCompatButton btnSubmit = dialog.findViewById(R.id.btn_dialog_submit);

        etName.setText(sessionManager.getUserName());
        etEmail.setText(sessionManager.getUserEmail());
        etPhone.setText(sessionManager.getUserPhone());
        etCity.setText(sessionManager.getUserCity());

        btnSubmit.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String city = etCity.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty()) {
                Toast.makeText(getContext(), "Por favor completa tu nombre y correo", Toast.LENGTH_SHORT).show();
                return;
            }

            sessionManager.createLoginSession(name, email, phone, city, "8472910 CBBA");
            Toast.makeText(getContext(), "¡Sesión iniciada con éxito! Bienvenido " + name, Toast.LENGTH_SHORT).show();
            loadUserData();
            dialog.dismiss();
        });

        dialog.show();
    }
}
