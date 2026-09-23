package com.example.miappbiker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.LinearLayout;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    public static final String EXTRA_PRODUCT_TITLE = "extra_product_title";
    public static final String EXTRA_PRODUCT_IMAGE = "extra_product_image";
    public static final String EXTRA_PRODUCT_PRICE = "extra_product_price";
    public static final String EXTRA_PRODUCT_COLOR = "extra_product_color";
    public static final String EXTRA_PRODUCT_QTY = "extra_product_qty";

    private LinearLayout llCartItemsContainer;
    private TextView tvSubtotal;
    private TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cart_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        llCartItemsContainer = findViewById(R.id.ll_cart_items_container);
        tvSubtotal = findViewById(R.id.tv_cart_subtotal);
        tvTotal = findViewById(R.id.tv_cart_total);

        checkInitialProduct();
        renderCartItems();
        setupCheckoutButton();
        setupNavigation();
    }

    private void checkInitialProduct() {
        Intent intent = getIntent();
        String title = intent.getStringExtra(EXTRA_PRODUCT_TITLE);
        if (title != null) {
            int imageRes = intent.getIntExtra(EXTRA_PRODUCT_IMAGE, R.drawable.img_moto_1);
            String price = intent.getStringExtra(EXTRA_PRODUCT_PRICE);
            String color = intent.getStringExtra(EXTRA_PRODUCT_COLOR);
            if (price == null) price = "$4,800 USD";
            if (color == null) color = "Naranja Racing";
            
            int unitPrice = 4800;
            try {
                unitPrice = Integer.parseInt(price.replaceAll("[^0-9]", ""));
            } catch (Exception ignored) {}

            CartManager.getInstance().addItem(title, title, imageRes, price, unitPrice, color, "Producto", 1);
        }
    }

    private void renderCartItems() {
        llCartItemsContainer.removeAllViews();
        List<CartItem> items = CartManager.getInstance().getItems();

        if (items.isEmpty()) {
            TextView tvEmpty = new TextView(this);
            tvEmpty.setText("Tu carrito está vacío 🛒");
            tvEmpty.setTextSize(16);
            tvEmpty.setGravity(android.view.Gravity.CENTER);
            tvEmpty.setPadding(0, 40, 0, 40);
            llCartItemsContainer.addView(tvEmpty);
            updatePriceDisplay();
            return;
        }

        for (int i = 0; i < items.size(); i++) {
            final int pos = i;
            CartItem item = items.get(i);

            androidx.cardview.widget.CardView card = new androidx.cardview.widget.CardView(this);
            LinearLayout.LayoutParams cardLp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            cardLp.setMargins(0, 0, 0, 16);
            card.setLayoutParams(cardLp);
            card.setRadius(32f);
            card.setCardElevation(4f);
            card.setCardBackgroundColor(android.graphics.Color.WHITE);

            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setPadding(20, 20, 20, 20);
            row.setGravity(android.view.Gravity.CENTER_VERTICAL);

            ImageView iv = new ImageView(this);
            LinearLayout.LayoutParams ivLp = new LinearLayout.LayoutParams(160, 160);
            iv.setLayoutParams(ivLp);
            iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
            iv.setImageResource(item.getImageRes());
            row.addView(iv);

            LinearLayout info = new LinearLayout(this);
            LinearLayout.LayoutParams infoLp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
            infoLp.setMargins(20, 0, 20, 0);
            info.setLayoutParams(infoLp);
            info.setOrientation(LinearLayout.VERTICAL);

            TextView tvTitle = new TextView(this);
            tvTitle.setText(item.getName().toUpperCase());
            tvTitle.setTextColor(android.graphics.Color.BLACK);
            tvTitle.setTextSize(13.5f);
            tvTitle.setTypeface(null, android.graphics.Typeface.BOLD);
            info.addView(tvTitle);

            TextView tvColor = new TextView(this);
            tvColor.setText("Color: " + item.getColor());
            tvColor.setTextColor(android.graphics.Color.DKGRAY);
            tvColor.setTextSize(11.5f);
            info.addView(tvColor);

            TextView tvPrice = new TextView(this);
            tvPrice.setText("$" + String.format("%,d", item.getTotalPrice()) + " USD (" + String.format("%,d", (int)Math.round(item.getTotalPrice()*6.96)) + " Bs.)");
            tvPrice.setTextColor(android.graphics.Color.parseColor("#D97706"));
            tvPrice.setTextSize(12.5f);
            tvPrice.setTypeface(null, android.graphics.Typeface.BOLD);
            info.addView(tvPrice);

            row.addView(info);

            // Quantity buttons
            LinearLayout qtyLayout = new LinearLayout(this);
            qtyLayout.setOrientation(LinearLayout.VERTICAL);
            qtyLayout.setGravity(android.view.Gravity.CENTER);

            TextView btnPlus = new TextView(this);
            btnPlus.setText("+");
            btnPlus.setTextSize(16);
            btnPlus.setGravity(android.view.Gravity.CENTER);
            btnPlus.setBackgroundResource(R.drawable.bg_pill_btn);
            btnPlus.setLayoutParams(new LinearLayout.LayoutParams(64, 64));
            btnPlus.setOnClickListener(v -> {
                CartManager.getInstance().updateQuantity(pos, 1);
                renderCartItems();
            });

            TextView tvQty = new TextView(this);
            tvQty.setText(String.valueOf(item.getQuantity()));
            tvQty.setTextSize(14);
            tvQty.setTypeface(null, android.graphics.Typeface.BOLD);
            tvQty.setGravity(android.view.Gravity.CENTER);
            tvQty.setPadding(0, 4, 0, 4);

            TextView btnMinus = new TextView(this);
            btnMinus.setText("-");
            btnMinus.setTextSize(16);
            btnMinus.setGravity(android.view.Gravity.CENTER);
            btnMinus.setBackgroundResource(R.drawable.bg_pill_btn);
            btnMinus.setLayoutParams(new LinearLayout.LayoutParams(64, 64));
            btnMinus.setOnClickListener(v -> {
                CartManager.getInstance().updateQuantity(pos, -1);
                renderCartItems();
            });

            qtyLayout.addView(btnPlus);
            qtyLayout.addView(tvQty);
            qtyLayout.addView(btnMinus);

            row.addView(qtyLayout);
            card.addView(row);
            llCartItemsContainer.addView(card);
        }

        updatePriceDisplay();
    }

    private void updatePriceDisplay() {
        int totalUSD = CartManager.getInstance().getTotalUSD();
        int totalBs = CartManager.getInstance().getTotalBs();

        String formattedUSD = "$" + String.format("%,d", totalUSD) + " USD";
        String formattedBs = "(" + String.format("%,d", totalBs) + " Bs.)";

        tvSubtotal.setText(formattedUSD);
        tvTotal.setText(formattedUSD + " " + formattedBs);
    }

    private void setupCheckoutButton() {
        EditText etName = findViewById(R.id.et_billing_name);
        EditText etNit = findViewById(R.id.et_billing_nit);
        EditText etPhone = findViewById(R.id.et_billing_phone);
        RadioGroup rgPayment = findViewById(R.id.rg_payment_methods);
        AppCompatButton btnConfirm = findViewById(R.id.btn_confirm_checkout);

        btnConfirm.setOnClickListener(v -> {
            List<CartItem> items = CartManager.getInstance().getItems();
            if (items.isEmpty()) {
                Toast.makeText(this, "⚠️ Tu carrito está vacío", Toast.LENGTH_SHORT).show();
                return;
            }

            String name = etName.getText().toString().trim();
            String nit = etNit.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();

            if (name.isEmpty()) {
                name = "Marcelo Biker";
            }

            int selectedId = rgPayment.getCheckedRadioButtonId();
            RadioButton selectedRb = findViewById(selectedId);
            String paymentName = selectedRb != null ? selectedRb.getText().toString() : "QR Simple";

            CartItem first = items.get(0);
            Intent intent = new Intent(CartActivity.this, BranchesActivity.class);
            intent.putExtra(BranchesActivity.EXTRA_PRODUCT_TITLE, first.getName());
            intent.putExtra(BranchesActivity.EXTRA_PRODUCT_IMAGE, first.getImageRes());
            intent.putExtra(BranchesActivity.EXTRA_PRODUCT_PRICE, "$" + String.format("%,d", CartManager.getInstance().getTotalUSD()) + " USD");
            intent.putExtra(BranchesActivity.EXTRA_PRODUCT_COLOR, first.getColor());
            intent.putExtra(BranchesActivity.EXTRA_PRODUCT_QTY, CartManager.getInstance().getTotalCount());
            intent.putExtra(BranchesActivity.EXTRA_BILLING_NAME, name);
            intent.putExtra(BranchesActivity.EXTRA_BILLING_NIT, nit.isEmpty() ? "S/N" : nit);
            intent.putExtra(BranchesActivity.EXTRA_BILLING_PHONE, phone.isEmpty() ? "76543210" : phone);
            intent.putExtra(BranchesActivity.EXTRA_PAY_METHOD, paymentName);
            startActivity(intent);
        });
    }

    private void setupNavigation() {
        findViewById(R.id.btn_cart_back).setOnClickListener(v -> finish());

        FrameLayout btnHome = findViewById(R.id.nav_btn_home);
        if (btnHome != null) {
            btnHome.setOnClickListener(v -> {
                Intent intent = new Intent(CartActivity.this, HomeActivity.class);
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
