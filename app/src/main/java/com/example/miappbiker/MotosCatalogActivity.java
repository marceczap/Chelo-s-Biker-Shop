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
        LinearLayout container = findViewById(R.id.ll_motos_grid_container);
        container.removeAllViews();

        int margin = (int) (5 * getResources().getDisplayMetrics().density);

        for (int i = 0; i < motosList.length; i += 2) {
            LinearLayout row = new LinearLayout(this);
            LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            rowParams.setMargins(0, 0, 0, (int) (6 * getResources().getDisplayMetrics().density));
            row.setLayoutParams(rowParams);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setWeightSum(2.0f);

            // Item 1 (Left)
            View card1 = createMotoCard(row, motosList[i]);
            LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
            lp1.setMargins(margin, margin, margin, margin);
            row.addView(card1, lp1);

            // Item 2 (Right if exists)
            if (i + 1 < motosList.length) {
                View card2 = createMotoCard(row, motosList[i + 1]);
                LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
                lp2.setMargins(margin, margin, margin, margin);
                row.addView(card2, lp2);
            } else {
                View placeholder = new View(this);
                LinearLayout.LayoutParams placeLp = new LinearLayout.LayoutParams(0, 1, 1.0f);
                placeLp.setMargins(margin, margin, margin, margin);
                row.addView(placeholder, placeLp);
            }

            container.addView(row);
        }
    }

    private View createMotoCard(ViewGroup parent, MotoItem moto) {
        View cardView = LayoutInflater.from(this).inflate(R.layout.item_catalog_card, parent, false);

        ImageView ivImage = cardView.findViewById(R.id.iv_card_image);
        TextView tvTitle = cardView.findViewById(R.id.tv_card_title);
        TextView tvSubtitle = cardView.findViewById(R.id.tv_card_subtitle);
        LinearLayout llSpecs = cardView.findViewById(R.id.ll_card_specs);
        TextView tvPrice = cardView.findViewById(R.id.tv_card_price);

        ivImage.setImageResource(moto.imageRes);
        tvTitle.setText(moto.name.toUpperCase());
        tvSubtitle.setText("caracteristicas");
        tvPrice.setText(moto.price);

        llSpecs.removeAllViews();
        for (String spec : moto.specs) {
            TextView tvSpec = new TextView(this);
            tvSpec.setText("• " + spec);
            tvSpec.setTextColor(Color.parseColor("#555555"));
            tvSpec.setTextSize(9.5f);
            tvSpec.setGravity(Gravity.CENTER);
            tvSpec.setPadding(0, 1, 0, 1);
            llSpecs.addView(tvSpec);
        }

        cardView.setOnClickListener(v -> {
            Intent intent = new Intent(MotosCatalogActivity.this, ProductDetailActivity.class);
            intent.putExtra(ProductDetailActivity.EXTRA_TITLE, moto.name);
            intent.putExtra(ProductDetailActivity.EXTRA_IMAGE_RES, moto.imageRes);
            intent.putExtra(ProductDetailActivity.EXTRA_SPECS, moto.specs);
            intent.putExtra(ProductDetailActivity.EXTRA_PRICE, moto.price);
            intent.putExtra(ProductDetailActivity.EXTRA_CATEGORY, "MOTOS");
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
                Intent intent = new Intent(MotosCatalogActivity.this, CartActivity.class);
                startActivity(intent);
            });
        }

        FrameLayout btnHome = findViewById(R.id.nav_btn_home);
        if (btnHome != null) {
            btnHome.setOnClickListener(v -> {
                Intent intent = new Intent(MotosCatalogActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            });
        }

        FrameLayout btnProfile = findViewById(R.id.nav_btn_profile);
        if (btnProfile != null) {
            btnProfile.setOnClickListener(v -> {
                Intent intent = new Intent(MotosCatalogActivity.this, ProfileActivity.class);
                startActivity(intent);
            });
        }

        FrameLayout btnLocations = findViewById(R.id.nav_btn_locations);
        if (btnLocations != null) {
            btnLocations.setOnClickListener(v -> HamburgerMenuBottomSheet.showMenu(getSupportFragmentManager()));
        }
    }
}
