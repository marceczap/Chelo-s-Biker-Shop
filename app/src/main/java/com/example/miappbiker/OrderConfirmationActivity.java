package com.example.miappbiker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class OrderConfirmationActivity extends AppCompatActivity {

    private boolean isDetailsExpanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_confirmation);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.confirmation_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Get Intent Extras
        Intent intent = getIntent();
        String orderId = intent.getStringExtra("order_id");
        if (orderId == null || orderId.isEmpty()) {
            orderId = "CHELO-" + (100000 + (int)(Math.random() * 900000));
        }

        String billingName = intent.getStringExtra("billing_name");
        String billingCI = intent.getStringExtra("billing_ci");
        String billingPhone = intent.getStringExtra("billing_phone");
        String billingEmail = intent.getStringExtra("billing_email");

        String productTitle = intent.getStringExtra("product_title");
        String productPrice = intent.getStringExtra("product_price");
        String productColor = intent.getStringExtra("product_color");
        int quantity = intent.getIntExtra("quantity", 1);
        int productImg = intent.getIntExtra("product_img", R.drawable.moto_ktm_enduro);

        String paymentMethod = intent.getStringExtra("payment_method");
        String cityName = intent.getStringExtra("city_name");
        String placeCode = intent.getStringExtra("place_code");
        String placeAddress = intent.getStringExtra("place_address");

        SessionManager session = new SessionManager(this);
        if (billingName == null || billingName.isEmpty()) billingName = session.getUserName();
        if (billingCI == null || billingCI.isEmpty()) billingCI = session.getUserCI();
        if (billingPhone == null || billingPhone.isEmpty()) billingPhone = session.getUserPhone();
        if (billingEmail == null || billingEmail.isEmpty()) billingEmail = session.getUserEmail();
        if (productTitle == null || productTitle.isEmpty()) productTitle = "KTM 250 XC-F BIKER PRO";
        if (productPrice == null || productPrice.isEmpty()) productPrice = "$4,800 USD";
        if (productColor == null || productColor.isEmpty()) productColor = "Naranja Biker";
        if (paymentMethod == null || paymentMethod.isEmpty()) paymentMethod = "QR Simple / Transferencia";
        if (cityName == null || cityName.isEmpty()) cityName = "La Paz";
        if (placeCode == null || placeCode.isEmpty()) placeCode = "FW67+66F La Paz";
        if (placeAddress == null || placeAddress.isEmpty()) placeAddress = "Av. Rafael Pabón, Megacenter Irpavi";

        // 2. Bind Summary Views
        TextView tvOrderCode = findViewById(R.id.tv_order_code);
        ImageView ivProductImg = findViewById(R.id.iv_confirm_product_img);
        TextView tvProductTitle = findViewById(R.id.tv_confirm_product_title);
        TextView tvProductDetails = findViewById(R.id.tv_confirm_product_details);
        TextView tvProductPrice = findViewById(R.id.tv_confirm_product_price);

        tvOrderCode.setText(orderId);
        ivProductImg.setImageResource(productImg);
        tvProductTitle.setText(productTitle);
        tvProductDetails.setText("x" + quantity + " • " + productColor);
        tvProductPrice.setText(productPrice);

        // 3. Bind Detailed Breakdown Views
        TextView tvBuyerName = findViewById(R.id.tv_detail_buyer_name);
        TextView tvBuyerCI = findViewById(R.id.tv_detail_buyer_ci);
        TextView tvBuyerPhone = findViewById(R.id.tv_detail_buyer_phone);
        TextView tvBuyerEmail = findViewById(R.id.tv_detail_buyer_email);

        TextView tvBranchCity = findViewById(R.id.tv_detail_branch_city);
        TextView tvBranchAddress = findViewById(R.id.tv_detail_branch_address);
        TextView tvBranchCode = findViewById(R.id.tv_detail_branch_code);

        TextView tvPayMethod = findViewById(R.id.tv_detail_pay_method);
        TextView tvSubtotal = findViewById(R.id.tv_detail_subtotal);
        TextView tvTotal = findViewById(R.id.tv_detail_total);

        tvBuyerName.setText(billingName);
        tvBuyerCI.setText(billingCI);
        tvBuyerPhone.setText(billingPhone);
        tvBuyerEmail.setText(billingEmail);

        tvBranchCity.setText(cityName);
        tvBranchAddress.setText(placeAddress);
        tvBranchCode.setText(placeCode);

        tvPayMethod.setText(paymentMethod);
        tvSubtotal.setText(productPrice);
        tvTotal.setText(productPrice);

        // 4. Collapsible Breakdown Logic
        CardView btnToggleDetails = findViewById(R.id.btn_toggle_details);
        TextView tvToggleText = findViewById(R.id.tv_toggle_details_text);
        ImageView ivToggleArrow = findViewById(R.id.iv_toggle_arrow);
        LinearLayout llExpandedDetails = findViewById(R.id.ll_expanded_details);

        // Start expanded so the user sees everything immediately, or toggleable
        isDetailsExpanded = true;
        llExpandedDetails.setVisibility(View.VISIBLE);
        tvToggleText.setText(R.string.confirm_btn_hide_details);
        ivToggleArrow.setRotation(90f);

        btnToggleDetails.setOnClickListener(v -> {
            isDetailsExpanded = !isDetailsExpanded;
            if (isDetailsExpanded) {
                llExpandedDetails.setVisibility(View.VISIBLE);
                tvToggleText.setText(R.string.confirm_btn_hide_details);
                ivToggleArrow.animate().rotation(90f).setDuration(200).start();
            } else {
                llExpandedDetails.setVisibility(View.GONE);
                tvToggleText.setText(R.string.confirm_btn_toggle_details);
                ivToggleArrow.animate().rotation(0f).setDuration(200).start();
            }
        });

        // 5. Navigation Buttons
        findViewById(R.id.btn_confirm_back).setOnClickListener(v -> goHome());
        findViewById(R.id.btn_confirm_go_home).setOnClickListener(v -> goHome());
        findViewById(R.id.btn_confirm_go_profile).setOnClickListener(v -> {
            Intent profileIntent = new Intent(OrderConfirmationActivity.this, ProfileActivity.class);
            startActivity(profileIntent);
            finish();
        });
    }

    private void goHome() {
        Intent homeIntent = new Intent(OrderConfirmationActivity.this, HomeActivity.class);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(homeIntent);
        finish();
    }
}
