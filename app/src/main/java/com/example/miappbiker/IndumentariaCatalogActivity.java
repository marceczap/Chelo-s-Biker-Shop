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

public class IndumentariaCatalogActivity extends AppCompatActivity {

    public static class GearItem {
        String name;
        int imageRes;
        String[] specs;
        String price;

        public GearItem(String name, int imageRes, String[] specs, String price) {
            this.name = name;
            this.imageRes = imageRes;
            this.specs = specs;
            this.price = price;
        }
    }

    private final GearItem[] gearList = new GearItem[]{
            new GearItem("Chaqueta Alpinestars GP Plus R v3", R.drawable.gear_chaqueta_cuero,
                    new String[]{"Material: Cuero Bovino 1.3mm", "Protección: Nucleon Flex Plus CE", "Zonas: Paneles Stretch Elásticos", "Regulación: Cintura con Velcro", "Uso: Racing / Deportivo"}, "$520 USD"),

            new GearItem("Guantes Dainese Full Metal 6", R.drawable.gear_guantes_racing,
                    new String[]{"Material: Piel de Cabra + Kevlar", "Nudillos: Titanio y Fibra Carbono", "Costuras: Fibra de Aramida", "Sistema: Distorsión Meñique DCP", "Homologación: CE Cat. II"}, "$390 USD"),

            new GearItem("Botas Alpinestars Supertech R", R.drawable.gear_botas_supertech,
                    new String[]{"Construcción: Microfibra Técnica", "Seguridad: Botín Interno Biomecánico", "Deslizador: TPU Reemplazable", "Suela: Agarre Compuesto Liviano", "Uso: Circuito y Pista"}, "$550 USD"),

            new GearItem("Mono Dainese Laguna Seca 5 1PC", R.drawable.gear_traje_monomono,
                    new String[]{"Material: Cuero Vacuno Tutu", "Placas: Titanio en Hombros y Codos", "Ventilación: Perforado Localizado", "Joroba: Aerodinámica con Hidrobag", "Nivel: Competición MotoGP"}, "$1,390 USD"),

            new GearItem("Pantalón Kevlar Rev'it Jeans", R.drawable.gear_pantalon_kevlar,
                    new String[]{"Tejido: Cordura Denim 12.5oz", "Refuerzo: PWR|shield Anti-abrasión", "Protecciones: SEESMART CE Nivel 1", "Corte: Slim Fit Urbano Cómodo", "Costuras: Triples Reforzadas"}, "$230 USD"),

            new GearItem("Chaqueta Dainese Carve Master 3", R.drawable.gear_chaqueta_textil,
                    new String[]{"Membrana: GORE-TEX Impermeable", "Forro: Térmico Cuello Desmontable", "Protección: Pro-Armor Nivel 2", "Ventilación: Entradas en Pecho y Espalda", "Uso: Touring 4 Estaciones"}, "$640 USD"),

            new GearItem("Botas Touring Forma Adventure", R.drawable.gear_botas_adventure,
                    new String[]{"Cuero: Tratado Graso Vintage", "Membrana: Drytex 100% Impermeable", "Cierres: Plásticos GH Regulables", "Suela: Doble Densidad Antideslizante", "Uso: Maxi-Trail / Aventura"}, "$280 USD"),

            new GearItem("Chaleco Airbag Alpinestars Tech-Air 5", R.drawable.gear_chaleco_airbag,
                    new String[]{"Sistema: Autónomo Inalámbrico", "Sensores: 6 Sensores Integrados", "Cobertura: Espalda, Hombros y Pecho", "Conexión: Bluetooth con App Móvil", "Despliegue: 20 a 40 milisegundos"}, "$750 USD")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_indumentaria_catalog);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.indumentaria_catalog_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        populateGrid();
        setupNavigation();
    }

    private void populateGrid() {
        LinearLayout container = findViewById(R.id.ll_indum_grid_container);
        container.removeAllViews();

        for (int i = 0; i < gearList.length; i += 2) {
            LinearLayout row = new LinearLayout(this);
            row.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setWeightSum(2);
            row.setPadding(0, 8, 0, 16);

            // Item 1 (Left)
            row.addView(createGearCard(gearList[i]));

            // Item 2 (Right if exists)
            if (i + 1 < gearList.length) {
                row.addView(createGearCard(gearList[i + 1]));
            } else {
                View placeholder = new View(this);
                placeholder.setLayoutParams(new LinearLayout.LayoutParams(0, 1, 1));
                row.addView(placeholder);
            }

            container.addView(row);
        }
    }

    private View createGearCard(GearItem item) {
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
        img.setImageResource(item.imageRes);
        cardFrame.addView(img);

        card.addView(cardFrame);

        // Name
        TextView tvName = new TextView(this);
        tvName.setText(item.name);
        tvName.setTextColor(Color.parseColor("#111111"));
        tvName.setTextSize(12);
        tvName.setTypeface(null, android.graphics.Typeface.BOLD);
        tvName.setPadding(2, 6, 2, 2);
        card.addView(tvName);

        // Price
        TextView tvPrice = new TextView(this);
        tvPrice.setText(item.price);
        tvPrice.setTextColor(Color.parseColor("#d97706")); // Golden Amber
        tvPrice.setTextSize(11);
        tvPrice.setTypeface(null, android.graphics.Typeface.BOLD);
        tvPrice.setPadding(2, 0, 2, 2);
        card.addView(tvPrice);

        // Specifications List
        for (String spec : item.specs) {
            TextView tvSpec = new TextView(this);
            tvSpec.setText("• " + spec);
            tvSpec.setTextColor(Color.parseColor("#444444"));
            tvSpec.setTextSize(9.5f);
            tvSpec.setPadding(2, 1, 2, 1);
            card.addView(tvSpec);
        }

        View.OnClickListener openDetail = v -> {
            Intent intent = new Intent(IndumentariaCatalogActivity.this, ProductDetailActivity.class);
            intent.putExtra(ProductDetailActivity.EXTRA_TITLE, item.name);
            intent.putExtra(ProductDetailActivity.EXTRA_IMAGE_RES, item.imageRes);
            intent.putExtra(ProductDetailActivity.EXTRA_SPECS, item.specs);
            intent.putExtra(ProductDetailActivity.EXTRA_PRICE, item.price);
            intent.putExtra(ProductDetailActivity.EXTRA_CATEGORY, "INDUMENTARIA");
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
                Intent intent = new Intent(IndumentariaCatalogActivity.this, HomeActivity.class);
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
