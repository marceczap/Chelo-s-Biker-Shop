package com.example.miappbiker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MotosCatalogActivity extends AppCompatActivity {

    public static class MotoItem {
        String name;
        int imageRes;
        String[] specs;
        String price;

        public MotoItem(String name, int imageRes, String[] specs, String price) {
            this.name = name;
            this.imageRes = imageRes;
            this.specs = specs;
            this.price = price;
        }
    }

    private final MotoItem[] motosList = new MotoItem[]{
            new MotoItem("BMW S1000RR Superbike", R.drawable.moto_bmw_s1000rr,
                    new String[]{"Cilindrada: 999 cc", "Potencia: 205 HP", "Frenos: ABS Pro Dual", "Transmisión: 6 vel.", "Peso: 197 kg"}, "$22,500 USD"),

            new MotoItem("Kawasaki Ninja ZX-6R", R.drawable.moto_kawasaki_ninja,
                    new String[]{"Cilindrada: 636 cc", "Potencia: 130 HP", "Frenos: Nissin ABS", "Control Tracción KTRC", "Chasis: Aluminio"}, "$14,800 USD"),

            new MotoItem("BMW R 1250 GS Adventure", R.drawable.moto_bmw_gs,
                    new String[]{"Cilindrada: 1254 cc Boxer", "Potencia: 136 HP", "Tanque: 30 Litros", "Suspensión: Dynamic ESA", "Transmisión: Cardán"}, "$26,900 USD"),

            new MotoItem("Kawasaki Z250 Naked", R.drawable.moto_kawasaki_z,
                    new String[]{"Cilindrada: 249 cc", "Motor: Bicilíndrico DOHC", "Frenos: Disco Lobulado", "Tablero: LCD Digital", "Consumo: 30 km/L"}, "$5,200 USD"),

            new MotoItem("Honda CRF 450R Competition", R.drawable.moto_honda_crf,
                    new String[]{"Cilindrada: 449 cc Unicam", "Arranque: Eléctrico", "Chasis: Aluminio Doble", "Inyección: PGM-FI", "Peso: 105 kg"}, "$9,800 USD"),

            new MotoItem("KTM Enduro 250 XC", R.drawable.moto_ktm_enduro,
                    new String[]{"Cilindrada: 249 cc 2T", "Inyección: TPI Electrónica", "Suspensión: WP XPLOR", "Frenos: Brembo", "Transmisión: 6 vel."}, "$10,400 USD"),

            new MotoItem("Ducati Panigale V4", R.drawable.moto_ducati_panigale,
                    new String[]{"Cilindrada: 1103 cc V4", "Potencia: 214 HP", "Electrónica: Ducati EVO", "QuickShift: Up/Down", "Frenos: Brembo Stylema"}, "$28,300 USD"),

            new MotoItem("Yamaha YZF-R6 Sport", R.drawable.moto_yamaha_r6,
                    new String[]{"Cilindrada: 599 cc", "Potencia: 118 HP", "Modos D-Mode", "Control Tracción TCS", "Horquilla: KYB 43mm"}, "$13,900 USD"),

            new MotoItem("KTM Power 250CC", R.drawable.img_moto_1,
                    new String[]{"Cilindrada: 248 cc", "Motor a varilla", "Tanque: 13 Litros", "Frenos a disco", "Tablero digital"}, "$4,800 USD"),

            new MotoItem("City Scooter 125", R.drawable.moto_scooter_city,
                    new String[]{"Cilindrada: 124 cc", "Transmisión: Automática CVT", "Consumo: 45 km/L", "Freno: Disco delantero", "Baúl: Bajo asiento"}, "$1,650 USD")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_motos_catalog);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.motos_catalog_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        populateGrid();
        setupNavigation();
    }

    private void populateGrid() {
        LinearLayout container = findViewById(R.id.ll_motos_grid_container);
        container.removeAllViews();

        for (int i = 0; i < motosList.length; i += 2) {
            LinearLayout row = new LinearLayout(this);
            row.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setWeightSum(2);
            row.setPadding(0, 8, 0, 16);

            // Item 1 (Left)
            row.addView(createMotoCard(motosList[i]));

            // Item 2 (Right if exists)
            if (i + 1 < motosList.length) {
                row.addView(createMotoCard(motosList[i + 1]));
            } else {
                View placeholder = new View(this);
                placeholder.setLayoutParams(new LinearLayout.LayoutParams(0, 1, 1));
                row.addView(placeholder);
            }

            container.addView(row);
        }
    }

    private View createMotoCard(MotoItem moto) {
        LinearLayout card = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        params.setMargins(6, 6, 6, 6);
        card.setLayoutParams(params);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setGravity(Gravity.CENTER_HORIZONTAL);
        card.setPadding(8, 10, 8, 12);
        card.setBackgroundResource(R.drawable.bg_rounded_card);
        card.setElevation(4f);

        // Moto Image
        ImageView iv = new ImageView(this);
        iv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 260));
        iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
        iv.setImageResource(moto.imageRes);
        card.addView(iv);

        // Subtitle "caracteristicas"
        TextView tvSub = new TextView(this);
        tvSub.setText("caracteristicas");
        tvSub.setTextColor(Color.parseColor("#111111"));
        tvSub.setTextSize(13);
        tvSub.setTypeface(android.graphics.Typeface.SERIF, android.graphics.Typeface.BOLD);
        tvSub.setPadding(0, 6, 0, 4);
        card.addView(tvSub);

        // Specs lines
        for (String spec : moto.specs) {
            TextView tvSpec = new TextView(this);
            tvSpec.setText("• " + spec);
            tvSpec.setTextColor(Color.parseColor("#444444"));
            tvSpec.setTextSize(10.5f);
            tvSpec.setGravity(Gravity.CENTER);
            card.addView(tvSpec);
        }

        // Price Tag
        TextView tvPrice = new TextView(this);
        tvPrice.setText(moto.price);
        tvPrice.setTextColor(Color.parseColor("#111111"));
        tvPrice.setTextSize(12.5f);
        tvPrice.setTypeface(android.graphics.Typeface.SERIF, android.graphics.Typeface.BOLD);
        tvPrice.setPadding(0, 6, 0, 0);
        card.addView(tvPrice);

        card.setOnClickListener(v -> {
            Intent intent = new Intent(MotosCatalogActivity.this, ProductDetailActivity.class);
            intent.putExtra(ProductDetailActivity.EXTRA_TITLE, moto.name);
            intent.putExtra(ProductDetailActivity.EXTRA_IMAGE_RES, moto.imageRes);
            intent.putExtra(ProductDetailActivity.EXTRA_SPECS, moto.specs);
            intent.putExtra(ProductDetailActivity.EXTRA_PRICE, moto.price);
            intent.putExtra(ProductDetailActivity.EXTRA_CATEGORY, "MOTOS");
            startActivity(intent);
        });

        return card;
    }

    private void setupNavigation() {
        findViewById(R.id.nav_btn_profile).setOnClickListener(v -> {
            Toast.makeText(this, "Navegando a Perfil 👤", Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.nav_btn_home).setOnClickListener(v -> {
            finish();
        });

        findViewById(R.id.nav_btn_locations).setOnClickListener(v -> {
            Toast.makeText(this, "Navegando a Sucursales 🗺️", Toast.LENGTH_SHORT).show();
        });
    }
}
