package com.example.miappbiker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    private CardView btnCatMotos;
    private CardView btnCatCascos;
    private CardView btnCatIndumentaria;
    private EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.home_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnCatMotos = findViewById(R.id.btn_cat_motos);
        btnCatCascos = findViewById(R.id.btn_cat_cascos);
        btnCatIndumentaria = findViewById(R.id.btn_cat_indumentaria);
        etSearch = findViewById(R.id.et_search);

        btnCatMotos.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MotosCatalogActivity.class);
            startActivity(intent);
        });

        btnCatCascos.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CascosCatalogActivity.class);
            startActivity(intent);
        });

        btnCatIndumentaria.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, IndumentariaCatalogActivity.class);
            startActivity(intent);
        });
    }
}
