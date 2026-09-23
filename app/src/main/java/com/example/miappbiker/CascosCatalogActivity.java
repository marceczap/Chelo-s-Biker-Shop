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

public class CascosCatalogActivity extends AppCompatActivity {

    public static class HelmetItem {
        String name;
        int imageRes;
        String[] specs;
        String price;

        public HelmetItem(String name, int imageRes, String[] specs, String price) {
            this.name = name;
            this.imageRes = imageRes;
            this.specs = specs;
            this.price = price;
        }
    }

    private final HelmetItem[] helmetsList = new HelmetItem[]{
            new HelmetItem("AGV Pista GP RR Carbon", R.drawable.casco_agv_pista,
                    new String[]{"Material: 100% Fibra Carbono", "Homologación: FIM / ECE 22.06", "Spoiler: PRO Aerodinámico", "Cierre: Doble D Titanio", "Peso: 1450g"}, "$1,450 USD"),

            new HelmetItem("Shoei X-Fourteen X-Spirit", R.drawable.casco_shoei_x14,
                    new String[]{"Calota: AIM+ 6 Capas", "Pantalla: CWR-F Pinlock", "Alerón: Estabilizador Regulable", "Interior: 3D Max-Dry", "Uso: Circuito Pro"}, "$890 USD"),

            new HelmetItem("Alpinestars Supertech S-M10", R.drawable.casco_alpinestars_sm10,
                    new String[]{"Material: Carbono 3K Multi-Densidad", "Seguridad: MIPS Rotacional", "Visera: Desmontable Impacto", "Ventilación: 19 Entradas", "Peso: 1260g"}, "$680 USD"),

            new HelmetItem("Bell Moto-9 Flex Off-Road", R.drawable.casco_bell_moto9,
                    new String[]{"Compuesto: Tri-Matrix Carbono", "Forro: Flex Impacto Progresivo", "Cierre: Magnético Magnefusion", "Ventilación: Velocity Flow", "Uso: Enduro / MX"}, "$620 USD"),

            new HelmetItem("HJC RPHA 11 Pro Carbon", R.drawable.casco_hjc_rpha,
                    new String[]{"Calota: P.I.M. Plus Fibra", "Visores: Oscuro + Pinlock Incl.", "Extracción: Rápida Emergencia", "Aerodinámica: Túnel de viento", "Cierre: Doble anilla"}, "$580 USD"),

            new HelmetItem("Arai RX-7V EVO Racing", R.drawable.casco_arai_rx7v,
                    new String[]{"Calota: PB-SNC2 Reforzada", "Difusores: Tipo 12 Aerodinámicos", "Pantalla: VAS-V Sistema Variable", "Norma: ECE 22.06 Oficial", "Artesanal Japonés"}, "$1,050 USD"),

            new HelmetItem("Fox Racing V3 RS Motocross", R.drawable.casco_fox_v3,
                    new String[]{"Seguridad: Sistema MIPS D3O", "Calota: MCT Fibra Carbono", "Rejillas: Inyectadas Ventilación", "Almohadillas: Antimicrobianas", "Uso: Competición MX"}, "$520 USD"),

            new HelmetItem("LS2 Thunder Carbon Pro", R.drawable.casco_ls2_thunder,
                    new String[]{"Material: 100% Fibra Carbono 6K", "Pantalla: Racing Clase A 3D", "Desbloqueo: Rápido de mejillas", "Cierre: Doble anilla D", "Peso: 1350g"}, "$420 USD")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cascos_catalog);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cascos_catalog_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        populateGrid();
        setupNavigation();
    }

    private void populateGrid() {
        LinearLayout container = findViewById(R.id.ll_cascos_grid_container);
        container.removeAllViews();

        for (int i = 0; i < helmetsList.length; i += 2) {
            LinearLayout row = new LinearLayout(this);
            row.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setWeightSum(2);
            row.setPadding(0, 8, 0, 16);

            // Item 1 (Left)
            row.addView(createHelmetCard(helmetsList[i]));

            // Item 2 (Right if exists)
            if (i + 1 < helmetsList.length) {
                row.addView(createHelmetCard(helmetsList[i + 1]));
            } else {
                View placeholder = new View(this);
                placeholder.setLayoutParams(new LinearLayout.LayoutParams(0, 1, 1));
                row.addView(placeholder);
            }

            container.addView(row);
        }
    }

    private View createHelmetCard(HelmetItem helmet) {
        LinearLayout card = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        params.setMargins(6, 6, 6, 6);
        card.setLayoutParams(params);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setBackgroundColor(Color.TRANSPARENT);

        // Card Container with Soft Glass / Border
        CardView cardFrame = new CardView(this);
        LinearLayout.LayoutParams frameParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 150);
        cardFrame.setLayoutParams(frameParams);
        cardFrame.setRadius(24);
        cardFrame.setCardElevation(4);
        cardFrame.setCardBackgroundColor(Color.WHITE);

        ImageView img = new ImageView(this);
        img.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        img.setImageResource(helmet.imageRes);
        cardFrame.addView(img);

        card.addView(cardFrame);

        // Helmet Name
        TextView tvName = new TextView(this);
        tvName.setText(helmet.name);
        tvName.setTextColor(Color.parseColor("#111111"));
        tvName.setTextSize(12);
        tvName.setTypeface(null, android.graphics.Typeface.BOLD);
        tvName.setPadding(2, 6, 2, 2);
        card.addView(tvName);

        // Price
        TextView tvPrice = new TextView(this);
        tvPrice.setText(helmet.price);
        tvPrice.setTextColor(Color.parseColor("#d97706")); // Golden Amber
        tvPrice.setTextSize(11);
        tvPrice.setTypeface(null, android.graphics.Typeface.BOLD);
        tvPrice.setPadding(2, 0, 2, 2);
        card.addView(tvPrice);

        // Specifications List
        for (String spec : helmet.specs) {
            TextView tvSpec = new TextView(this);
            tvSpec.setText("• " + spec);
            tvSpec.setTextColor(Color.parseColor("#444444"));
            tvSpec.setTextSize(9.5f);
            tvSpec.setPadding(2, 1, 2, 1);
            card.addView(tvSpec);
        }

        View.OnClickListener openDetail = v -> {
            Intent intent = new Intent(CascosCatalogActivity.this, ProductDetailActivity.class);
            intent.putExtra(ProductDetailActivity.EXTRA_TITLE, helmet.name);
            intent.putExtra(ProductDetailActivity.EXTRA_IMAGE_RES, helmet.imageRes);
            intent.putExtra(ProductDetailActivity.EXTRA_SPECS, helmet.specs);
            intent.putExtra(ProductDetailActivity.EXTRA_PRICE, helmet.price);
            intent.putExtra(ProductDetailActivity.EXTRA_CATEGORY, "CASCOS");
            startActivity(intent);
        };

        card.setOnClickListener(openDetail);

        // Action Button Ver / Detalle
        TextView btnAction = new TextView(this);
        LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        btnParams.setMargins(0, 6, 0, 0);
        btnAction.setLayoutParams(btnParams);
        btnAction.setBackgroundResource(R.drawable.bg_pill_btn);
        btnAction.setText("Ver Detalle");
        btnAction.setTextColor(Color.parseColor("#111111"));
        btnAction.setTextSize(10);
        btnAction.setGravity(Gravity.CENTER);
        btnAction.setPadding(4, 8, 4, 8);
        btnAction.setClickable(true);
        btnAction.setFocusable(true);
        btnAction.setOnClickListener(openDetail);
        card.addView(btnAction);

        return card;
    }

    private void setupNavigation() {
        ImageView btnBack = findViewById(R.id.btn_back_home);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        FrameLayout btnHome = findViewById(R.id.nav_btn_home);
        if (btnHome != null) {
            btnHome.setOnClickListener(v -> {
                Intent intent = new Intent(CascosCatalogActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            });
        }

        FrameLayout btnProfile = findViewById(R.id.nav_btn_profile);
        if (btnProfile != null) {
            btnProfile.setOnClickListener(v ->
                    Toast.makeText(this, "Ruta: Perfil de Usuario 👤", Toast.LENGTH_SHORT).show()
            );
        }

        FrameLayout btnMenu = findViewById(R.id.nav_btn_menu);
        if (btnMenu != null) {
            btnMenu.setOnClickListener(v ->
                    Toast.makeText(this, "Ruta: Sucursales y Ubicaciones 🗺️", Toast.LENGTH_SHORT).show()
            );
        }
    }
}
