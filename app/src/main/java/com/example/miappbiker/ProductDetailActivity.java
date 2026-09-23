package com.example.miappbiker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProductDetailActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "extra_title";
    public static final String EXTRA_IMAGE_RES = "extra_image_res";
    public static final String EXTRA_SPECS = "extra_specs";
    public static final String EXTRA_PRICE = "extra_price";
    public static final String EXTRA_CATEGORY = "extra_category";

    private String productTitle;
    private String productPrice;
    private String selectedColor = "Negro";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_detail);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.product_detail_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loadProductData();
        setupColorSelector();
        setupButtons();
    }

    private void loadProductData() {
        Intent intent = getIntent();
        productTitle = intent.getStringExtra(EXTRA_TITLE);
        int imageRes = intent.getIntExtra(EXTRA_IMAGE_RES, R.drawable.img_moto_1);
        String[] specs = intent.getStringArrayExtra(EXTRA_SPECS);
        productPrice = intent.getStringExtra(EXTRA_PRICE);

        if (productTitle == null) productTitle = "KTM POWER 250CC";
        if (productPrice == null) productPrice = "$4,800 USD";

        TextView tvTitle = findViewById(R.id.tv_detail_title);
        ImageView ivImage = findViewById(R.id.iv_detail_image);
        TextView tvPrice = findViewById(R.id.tv_detail_price);

        tvTitle.setText(productTitle.toUpperCase());
        ivImage.setImageResource(imageRes);
        tvPrice.setText(productPrice);

        // Load Specs into 5 textviews
        TextView[] specViews = new TextView[]{
                findViewById(R.id.tv_spec_1),
                findViewById(R.id.tv_spec_2),
                findViewById(R.id.tv_spec_3),
                findViewById(R.id.tv_spec_4),
                findViewById(R.id.tv_spec_5)
        };

        if (specs != null && specs.length > 0) {
            for (int i = 0; i < specViews.length; i++) {
                if (i < specs.length) {
                    specViews[i].setVisibility(View.VISIBLE);
                    specViews[i].setText(specs[i].toUpperCase());
                } else {
                    specViews[i].setVisibility(View.GONE);
                }
            }
        }
    }

    private void setupColorSelector() {
        FrameLayout btnBlack = findViewById(R.id.btn_color_black);
        FrameLayout btnRed = findViewById(R.id.btn_color_red);
        FrameLayout btnGreen = findViewById(R.id.btn_color_green);

        btnBlack.setOnClickListener(v -> {
            selectedColor = "Negro";
            btnBlack.setScaleX(1.15f);
            btnBlack.setScaleY(1.15f);
            btnRed.setScaleX(1.0f);
            btnRed.setScaleY(1.0f);
            btnGreen.setScaleX(1.0f);
            btnGreen.setScaleY(1.0f);
            Toast.makeText(this, "Color seleccionado: Negro", Toast.LENGTH_SHORT).show();
        });

        btnRed.setOnClickListener(v -> {
            selectedColor = "Rojo";
            btnRed.setScaleX(1.15f);
            btnRed.setScaleY(1.15f);
            btnBlack.setScaleX(1.0f);
            btnBlack.setScaleY(1.0f);
            btnGreen.setScaleX(1.0f);
            btnGreen.setScaleY(1.0f);
            Toast.makeText(this, "Color seleccionado: Rojo", Toast.LENGTH_SHORT).show();
        });

        btnGreen.setOnClickListener(v -> {
            selectedColor = "Verde";
            btnGreen.setScaleX(1.15f);
            btnGreen.setScaleY(1.15f);
            btnBlack.setScaleX(1.0f);
            btnBlack.setScaleY(1.0f);
            btnRed.setScaleX(1.0f);
            btnRed.setScaleY(1.0f);
            Toast.makeText(this, "Color seleccionado: Verde", Toast.LENGTH_SHORT).show();
        });
    }

    private void setupButtons() {
        ImageView btnBack = findViewById(R.id.btn_detail_back);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        AppCompatButton btnAddToCart = findViewById(R.id.btn_add_to_cart);
        if (btnAddToCart != null) {
            btnAddToCart.setOnClickListener(v -> {
                int imgRes = getIntent().getIntExtra(EXTRA_IMAGE_RES, R.drawable.img_moto_1);
                int unitPrice = 4800;
                try {
                    String clean = productPrice.replaceAll("[^0-9]", "");
                    if (!clean.isEmpty()) {
                        unitPrice = Integer.parseInt(clean);
                    }
                } catch (Exception ignored) {}

                CartManager.getInstance().addItem(
                        productTitle,
                        productTitle,
                        imgRes,
                        productPrice,
                        unitPrice,
                        selectedColor,
                        "Producto",
                        1
                );

                new androidx.appcompat.app.AlertDialog.Builder(this)
                        .setTitle("¡Producto Agregado! 🛒")
                        .setMessage("Se agregó \"" + productTitle + "\" (" + selectedColor + ") a tu carrito.\n\nTotal productos en carrito: " + CartManager.getInstance().getTotalCount())
                        .setPositiveButton("Ir al Carrito 🛒", (dialog, which) -> {
                            Intent cartIntent = new Intent(ProductDetailActivity.this, CartActivity.class);
                            startActivity(cartIntent);
                        })
                        .setNegativeButton("Seguir Comprando 🏍️", null)
                        .show();
            });
        }

        FrameLayout btnHome = findViewById(R.id.nav_btn_home);
        if (btnHome != null) {
            btnHome.setOnClickListener(v -> {
                Intent intent = new Intent(ProductDetailActivity.this, HomeActivity.class);
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
