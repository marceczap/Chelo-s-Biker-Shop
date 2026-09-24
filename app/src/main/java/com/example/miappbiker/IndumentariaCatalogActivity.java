package com.example.miappbiker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
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
            new GearItem("Chaqueta Alpinestars GP Plus", R.drawable.gear_chaqueta_cuero,
                    new String[]{"Cuero Bovino 1.3mm", "Protección Nucleon CE", "Paneles Stretch", "Regulación Cintura", "Uso Racing / Sport"}, "$520 USD"),

            new GearItem("Guantes Dainese Full Metal 6", R.drawable.gear_guantes_racing,
                    new String[]{"Piel Cabra + Kevlar", "Nudillos Titanio/Carbono", "Costuras Fibra Aramida", "Control Distorsión DCP", "Homologación CE Cat II"}, "$390 USD"),

            new GearItem("Botas Alpinestars Supertech R", R.drawable.gear_botas_supertech,
                    new String[]{"Botín Interior Biomecánico", "Deslizadera TPU/Aluminio", "Microfibra Alta Abrasión", "Suela Agarre MotoGP", "Protección Espinilla"}, "$540 USD"),

            new GearItem("Mono Cuero Alpinestars GP Force", R.drawable.gear_traje_monomono,
                    new String[]{"1 Pieza Cuero 1.3mm", "Compatible Chaleco Tech-Air", "Joroba Aerodinámica Pro", "Protecciones CE Nivel 2", "Sliders Rodilla/Codo"}, "$980 USD"),

            new GearItem("Pantalón Kevlar Revit Detroit TF", R.drawable.gear_pantalon_kevlar,
                    new String[]{"Denim Cordura Stretch", "Refuerzos PWR|Shield", "Protección Rodilla Seesmart", "Triple Costura Seguridad", "Estilo Urbano Casual"}, "$230 USD"),

            new GearItem("Chaqueta Touring Klim Badlands", R.drawable.gear_chaqueta_textil,
                    new String[]{"Membrana Gore-Tex Pro 3L", "Refuerzos Superfabric", "Protección D3O Aero Pro", "12 Ventilaciones Activas", "Impermeable Total"}, "$1,150 USD"),

            new GearItem("Botas Adventure Sidi Adventure 2", R.drawable.gear_botas_adventure,
                    new String[]{"Membrana Gore-Tex 100%", "Sistema Flex Pivotante", "Cierre Doble Micrométrico", "Suela Antideslizante Enduro", "Uso Dual / Travesía"}, "$460 USD"),

            new GearItem("Chaleco Airbag Alpinestars Tech-Air 5", R.drawable.gear_chaleco_airbag,
                    new String[]{"Despliegue en 20-40ms", "6 Sensores Giroscópicos", "Bluetooth App Conectada", "Protección Espalda/Hombro", "Autónomo sin cables"}, "$750 USD")
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

    @Override
    protected void onResume() {
        super.onResume();
        updateCartBadge();
    }

    private void updateCartBadge() {
        TextView tvBadge = findViewById(R.id.tv_cart_badge);
        if (tvBadge != null) {
            int count = CartManager.getInstance().getTotalCount();
            tvBadge.setText(String.valueOf(count));
        }
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
            row.setPadding(0, 4, 0, 8);

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
        View cardView = LayoutInflater.from(this).inflate(R.layout.item_catalog_card, null, false);

        ImageView ivImage = cardView.findViewById(R.id.iv_card_image);
        TextView tvTitle = cardView.findViewById(R.id.tv_card_title);
        TextView tvSubtitle = cardView.findViewById(R.id.tv_card_subtitle);
        LinearLayout llSpecs = cardView.findViewById(R.id.ll_card_specs);
        TextView tvPrice = cardView.findViewById(R.id.tv_card_price);

        ivImage.setImageResource(item.imageRes);
        tvTitle.setText(item.name.toUpperCase());
        tvSubtitle.setText("especificaciones");
        tvPrice.setText(item.price);

        llSpecs.removeAllViews();
        for (String spec : item.specs) {
            TextView tvSpec = new TextView(this);
            tvSpec.setText("• " + spec);
            tvSpec.setTextColor(Color.parseColor("#555555"));
            tvSpec.setTextSize(9.5f);
            tvSpec.setGravity(Gravity.CENTER);
            tvSpec.setPadding(0, 1, 0, 1);
            llSpecs.addView(tvSpec);
        }

        cardView.setOnClickListener(v -> {
            Intent intent = new Intent(IndumentariaCatalogActivity.this, ProductDetailActivity.class);
            intent.putExtra(ProductDetailActivity.EXTRA_TITLE, item.name);
            intent.putExtra(ProductDetailActivity.EXTRA_IMAGE_RES, item.imageRes);
            intent.putExtra(ProductDetailActivity.EXTRA_SPECS, item.specs);
            intent.putExtra(ProductDetailActivity.EXTRA_PRICE, item.price);
            intent.putExtra(ProductDetailActivity.EXTRA_CATEGORY, "INDUMENTARIA");
            startActivity(intent);
        });

        return cardView;
    }

    private void setupNavigation() {
        ImageView btnBack = findViewById(R.id.btn_back_home);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        FrameLayout btnCart = findViewById(R.id.btn_floating_cart);
        if (btnCart != null) {
            btnCart.setOnClickListener(v -> {
                Intent intent = new Intent(IndumentariaCatalogActivity.this, CartActivity.class);
                startActivity(intent);
            });
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
            btnMenu.setOnClickListener(v -> {
                Intent intent = new Intent(IndumentariaCatalogActivity.this, BranchesActivity.class);
                startActivity(intent);
            });
        }
    }
}
