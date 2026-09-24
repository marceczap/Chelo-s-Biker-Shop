package com.example.miappbiker;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
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
    private int productImageRes;
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

    private void loadProductData() {
        Intent intent = getIntent();
        productTitle = intent.getStringExtra(EXTRA_TITLE);
        productImageRes = intent.getIntExtra(EXTRA_IMAGE_RES, R.drawable.img_moto_1);
        String[] specs = intent.getStringArrayExtra(EXTRA_SPECS);
        productPrice = intent.getStringExtra(EXTRA_PRICE);

        if (productTitle == null) productTitle = "KTM POWER 250CC";
        if (productPrice == null) productPrice = "$4,800 USD";

        TextView tvTitle = findViewById(R.id.tv_detail_title);
        ImageView ivImage = findViewById(R.id.iv_detail_image);
        TextView tvPrice = findViewById(R.id.tv_detail_price);

        tvTitle.setText(productTitle.toUpperCase());
        ivImage.setImageResource(productImageRes);
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

        FrameLayout btnCart = findViewById(R.id.btn_floating_cart);
        if (btnCart != null) {
            btnCart.setOnClickListener(v -> {
                Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
                startActivity(intent);
            });
        }

        AppCompatButton btnAddToCart = findViewById(R.id.btn_add_to_cart);
        if (btnAddToCart != null) {
            btnAddToCart.setOnClickListener(v -> executeFlyToCartAnimation());
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
            btnProfile.setOnClickListener(v -> {
                Intent intent = new Intent(ProductDetailActivity.this, ProfileActivity.class);
                startActivity(intent);
            });
        }

        FrameLayout btnMenu = findViewById(R.id.nav_btn_menu);
        if (btnMenu != null) {
            btnMenu.setOnClickListener(v -> {
                Intent intent = new Intent(ProductDetailActivity.this, BranchesActivity.class);
                startActivity(intent);
            });
        }
    }

    private void executeFlyToCartAnimation() {
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
                productImageRes,
                productPrice,
                unitPrice,
                selectedColor,
                "Producto",
                1
        );

        ImageView sourceView = findViewById(R.id.iv_detail_image);
        View targetCartBtn = findViewById(R.id.btn_floating_cart);
        FrameLayout animContainer = findViewById(R.id.fl_fly_animation_container);

        if (sourceView == null || targetCartBtn == null || animContainer == null) {
            updateCartBadge();
            Toast.makeText(this, "¡Agregado al Carrito! 🛒", Toast.LENGTH_SHORT).show();
            return;
        }

        int[] srcLoc = new int[2];
        sourceView.getLocationInWindow(srcLoc);

        int[] dstLoc = new int[2];
        targetCartBtn.getLocationInWindow(dstLoc);

        // Create transient flying image
        ImageView flyingImg = new ImageView(this);
        flyingImg.setImageResource(productImageRes);
        flyingImg.setScaleType(ImageView.ScaleType.FIT_CENTER);

        int flySize = (int) (90 * getResources().getDisplayMetrics().density);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(flySize, flySize);
        flyingImg.setLayoutParams(params);

        flyingImg.setX(srcLoc[0] + (sourceView.getWidth() - flySize) / 2f);
        flyingImg.setY(srcLoc[1] + (sourceView.getHeight() - flySize) / 2f);

        animContainer.addView(flyingImg);

        float targetX = dstLoc[0] + (targetCartBtn.getWidth() - flySize) / 2f;
        float targetY = dstLoc[1] + (targetCartBtn.getHeight() - flySize) / 2f;

        flyingImg.animate()
                .x(targetX)
                .y(targetY)
                .scaleX(0.22f)
                .scaleY(0.22f)
                .alpha(0.7f)
                .rotation(180f)
                .setDuration(550)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        animContainer.removeView(flyingImg);
                        updateCartBadge();

                        // Bounce animation on floating cart button
                        targetCartBtn.animate()
                                .scaleX(1.3f)
                                .scaleY(1.3f)
                                .setDuration(150)
                                .withEndAction(() -> targetCartBtn.animate()
                                        .scaleX(1.0f)
                                        .scaleY(1.0f)
                                        .setDuration(150)
                                        .start())
                                .start();

                        Toast.makeText(ProductDetailActivity.this, "¡Agregado al Carrito! 🛒 (" + CartManager.getInstance().getTotalCount() + " items)", Toast.LENGTH_SHORT).show();
                    }
                })
                .start();
    }
}
