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
                    new String[]{"100% Fibra Carbono", "Homologado FIM / ECE 22.06", "Spoiler PRO Aerodinámico", "Cierre Doble D Titanio", "Peso: 1450g"}, "$1,450 USD"),

            new HelmetItem("Shoei X-Fourteen Racing", R.drawable.casco_shoei_x14,
                    new String[]{"Calota AIM+ 6 Capas", "Pantalla CWR-F Pinlock", "Alerón Regulable", "Interior 3D Max-Dry", "Uso Circuito Pro"}, "$890 USD"),

            new HelmetItem("Alpinestars Supertech S-M10", R.drawable.casco_alpinestars_sm10,
                    new String[]{"Carbono 3K Multi-Densidad", "Sistema MIPS Rotacional", "Visera Desmontable", "19 Entradas Ventilación", "Peso: 1260g"}, "$680 USD"),

            new HelmetItem("Bell Moto-9 Flex Off-Road", R.drawable.casco_bell_moto9,
                    new String[]{"Compuesto Tri-Matrix", "Forro Flex Multi-Impacto", "Cierre Magnético", "Ventilación Velocity", "Uso Enduro / MX"}, "$620 USD"),

            new HelmetItem("HJC RPHA 11 Pro Carbon", R.drawable.casco_hjc_rpha,
                    new String[]{"Calota P.I.M. Plus Fibra", "Visores Oscuro + Pinlock", "Extracción Emergencia", "Túnel de Viento MotoGP", "Cierre Doble Anilla"}, "$580 USD"),

            new HelmetItem("Arai RX-7V EVO Racing", R.drawable.casco_arai_rx7v,
                    new String[]{"Calota PB-SNC2 Reforzada", "Difusores Tipo 12 Aero", "Pantalla VAS-V Variable", "Norma ECE 22.06 Oficial", "Artesanal Japonés"}, "$1,050 USD"),

            new HelmetItem("Fox Racing V3 RS Solids", R.drawable.casco_fox_v3,
                    new String[]{"Sistema MIPS D3O", "Calota MCT Fibra Carbono", "Rejillas Inyectadas", "Almohadillas Pro", "Competición MX"}, "$520 USD"),

            new HelmetItem("LS2 Thunder Carbon Pro", R.drawable.casco_ls2_thunder,
                    new String[]{"100% Fibra Carbono 6K", "Pantalla Racing Clase A", "Desbloqueo Rápido", "Cierre Doble Anilla D", "Peso: 1350g"}, "$420 USD")
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
        LinearLayout container = findViewById(R.id.ll_cascos_grid_container);
        container.removeAllViews();

        int margin = (int) (5 * getResources().getDisplayMetrics().density);

        for (int i = 0; i < helmetsList.length; i += 2) {
            LinearLayout row = new LinearLayout(this);
            LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            rowParams.setMargins(0, 0, 0, (int) (6 * getResources().getDisplayMetrics().density));
            row.setLayoutParams(rowParams);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setWeightSum(2.0f);

            // Item 1 (Left)
            View card1 = createHelmetCard(row, helmetsList[i]);
            LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
            lp1.setMargins(margin, margin, margin, margin);
            row.addView(card1, lp1);

            // Item 2 (Right if exists)
            if (i + 1 < helmetsList.length) {
                View card2 = createHelmetCard(row, helmetsList[i + 1]);
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

    private View createHelmetCard(ViewGroup parent, HelmetItem helmet) {
        View cardView = LayoutInflater.from(this).inflate(R.layout.item_catalog_card, parent, false);

        ImageView ivImage = cardView.findViewById(R.id.iv_card_image);
        TextView tvTitle = cardView.findViewById(R.id.tv_card_title);
        TextView tvSubtitle = cardView.findViewById(R.id.tv_card_subtitle);
        LinearLayout llSpecs = cardView.findViewById(R.id.ll_card_specs);
        TextView tvPrice = cardView.findViewById(R.id.tv_card_price);

        ivImage.setImageResource(helmet.imageRes);
        tvTitle.setText(helmet.name.toUpperCase());
        tvSubtitle.setText("especificaciones");
        tvPrice.setText(helmet.price);

        llSpecs.removeAllViews();
        for (String spec : helmet.specs) {
            TextView tvSpec = new TextView(this);
            tvSpec.setText("• " + spec);
            tvSpec.setTextColor(Color.parseColor("#555555"));
            tvSpec.setTextSize(9.5f);
            tvSpec.setGravity(Gravity.CENTER);
            tvSpec.setPadding(0, 1, 0, 1);
            llSpecs.addView(tvSpec);
        }

        cardView.setOnClickListener(v -> {
            Intent intent = new Intent(CascosCatalogActivity.this, ProductDetailActivity.class);
            intent.putExtra(ProductDetailActivity.EXTRA_TITLE, helmet.name);
            intent.putExtra(ProductDetailActivity.EXTRA_IMAGE_RES, helmet.imageRes);
            intent.putExtra(ProductDetailActivity.EXTRA_SPECS, helmet.specs);
            intent.putExtra(ProductDetailActivity.EXTRA_PRICE, helmet.price);
            intent.putExtra(ProductDetailActivity.EXTRA_CATEGORY, "CASCOS");
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
                Intent intent = new Intent(CascosCatalogActivity.this, CartActivity.class);
                startActivity(intent);
            });
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
            btnProfile.setOnClickListener(v -> {
                Intent intent = new Intent(CascosCatalogActivity.this, ProfileActivity.class);
                startActivity(intent);
            });
        }

        FrameLayout btnMenu = findViewById(R.id.nav_btn_menu);
        if (btnMenu != null) {
            btnMenu.setOnClickListener(v -> HamburgerMenuBottomSheet.showMenu(getSupportFragmentManager()));
        }
    }
}
