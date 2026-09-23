package com.example.miappbiker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BranchesActivity extends AppCompatActivity {

    public static final String EXTRA_PRODUCT_TITLE = "extra_cart_title";
    public static final String EXTRA_PRODUCT_IMAGE = "extra_cart_image";
    public static final String EXTRA_PRODUCT_PRICE = "extra_cart_price";
    public static final String EXTRA_PRODUCT_COLOR = "extra_cart_color";
    public static final String EXTRA_PRODUCT_QTY = "extra_cart_qty";
    public static final String EXTRA_BILLING_NAME = "extra_billing_name";
    public static final String EXTRA_BILLING_NIT = "extra_billing_nit";
    public static final String EXTRA_BILLING_PHONE = "extra_billing_phone";
    public static final String EXTRA_PAY_METHOD = "extra_pay_method";

    private String productTitle;
    private int productImageRes;
    private String productPrice;
    private String productColor;
    private int quantity;
    private String billingName;
    private String billingNit;
    private String billingPhone;
    private String payMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_branches);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.branches_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        readIntentData();
        setupBranchSelection();
    }

    private void readIntentData() {
        Intent intent = getIntent();
        productTitle = intent.getStringExtra(EXTRA_PRODUCT_TITLE);
        productImageRes = intent.getIntExtra(EXTRA_PRODUCT_IMAGE, R.drawable.img_moto_1);
        productPrice = intent.getStringExtra(EXTRA_PRODUCT_PRICE);
        productColor = intent.getStringExtra(EXTRA_PRODUCT_COLOR);
        quantity = intent.getIntExtra(EXTRA_PRODUCT_QTY, 1);
        billingName = intent.getStringExtra(EXTRA_BILLING_NAME);
        billingNit = intent.getStringExtra(EXTRA_BILLING_NIT);
        billingPhone = intent.getStringExtra(EXTRA_BILLING_PHONE);
        payMethod = intent.getStringExtra(EXTRA_PAY_METHOD);
    }

    private void setupBranchSelection() {
        ImageView btnBack = findViewById(R.id.btn_branches_back);
        btnBack.setOnClickListener(v -> finish());

        CardView cardLaPaz = findViewById(R.id.card_branch_lapaz);
        CardView cardElAlto = findViewById(R.id.card_branch_elalto);
        CardView cardCochabamba = findViewById(R.id.card_branch_cochabamba);

        cardLaPaz.setOnClickListener(v -> openBranchMap("La Paz"));
        cardElAlto.setOnClickListener(v -> openBranchMap("El Alto"));
        cardCochabamba.setOnClickListener(v -> openBranchMap("Cochabamba"));
    }

    private void openBranchMap(String cityName) {
        Toast.makeText(this, "Sucursal seleccionada: " + cityName + " 🗺️", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(BranchesActivity.this, BranchMapActivity.class);
        intent.putExtra(BranchMapActivity.EXTRA_CITY_NAME, cityName);
        intent.putExtra(EXTRA_PRODUCT_TITLE, productTitle);
        intent.putExtra(EXTRA_PRODUCT_IMAGE, productImageRes);
        intent.putExtra(EXTRA_PRODUCT_PRICE, productPrice);
        intent.putExtra(EXTRA_PRODUCT_COLOR, productColor);
        intent.putExtra(EXTRA_PRODUCT_QTY, quantity);
        intent.putExtra(EXTRA_BILLING_NAME, billingName);
        intent.putExtra(EXTRA_BILLING_NIT, billingNit);
        intent.putExtra(EXTRA_BILLING_PHONE, billingPhone);
        intent.putExtra(EXTRA_PAY_METHOD, payMethod);
        startActivity(intent);
    }
}
