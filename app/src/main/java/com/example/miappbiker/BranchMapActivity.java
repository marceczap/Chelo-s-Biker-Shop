package com.example.miappbiker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Random;

public class BranchMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static final String EXTRA_CITY_NAME = "extra_city_name";

    private String cityName = "La Paz";
    private String productTitle;
    private int productImageRes;
    private String productPrice;
    private String productColor;
    private int quantity;
    private String billingName;
    private String billingNit;
    private String billingPhone;
    private String payMethod;

    private String placeCode;
    private String placeAddress;
    private double lat;
    private double lng;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_branch_map);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.branch_map_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        readIntentData();
        setupMapDetails();
        setupGoogleMapFragment();
        setupButtons();
        setupNavigation();
    }

    private void readIntentData() {
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_CITY_NAME)) {
            cityName = intent.getStringExtra(EXTRA_CITY_NAME);
        }
        productTitle = intent.getStringExtra(BranchesActivity.EXTRA_PRODUCT_TITLE);
        productImageRes = intent.getIntExtra(BranchesActivity.EXTRA_PRODUCT_IMAGE, R.drawable.img_moto_1);
        productPrice = intent.getStringExtra(BranchesActivity.EXTRA_PRODUCT_PRICE);
        productColor = intent.getStringExtra(BranchesActivity.EXTRA_PRODUCT_COLOR);
        quantity = intent.getIntExtra(BranchesActivity.EXTRA_PRODUCT_QTY, 1);
        billingName = intent.getStringExtra(BranchesActivity.EXTRA_BILLING_NAME);
        billingNit = intent.getStringExtra(BranchesActivity.EXTRA_BILLING_NIT);
        billingPhone = intent.getStringExtra(BranchesActivity.EXTRA_BILLING_PHONE);
        payMethod = intent.getStringExtra(BranchesActivity.EXTRA_PAY_METHOD);

        if (billingName == null) billingName = "Marcelo Biker";
        if (productTitle == null) productTitle = "KTM POWER 250CC";
        if (productPrice == null) productPrice = "$4,800 USD";
        if (productColor == null) productColor = "Naranja Racing";
        if (payMethod == null) payMethod = "QR Simple";
    }

    private void setupMapDetails() {
        TextView tvCity = findViewById(R.id.tv_city_title);
        TextView tvCode = findViewById(R.id.tv_place_code);
        TextView tvAddress = findViewById(R.id.tv_place_address);

        tvCity.setText(cityName);

        if ("El Alto".equalsIgnoreCase(cityName)) {
            lat = -16.537243;
            lng = -68.172773;
            placeCode = "Panamericana El Alto";
            placeAddress = "Av. Panamericana & 6 de Marzo, Cruce Viacha, El Alto";
        } else if ("Cochabamba".equalsIgnoreCase(cityName)) {
            lat = -17.393845;
            lng = -66.157821;
            placeCode = "JR3R+HCQ Jordan, Cochabamba";
            placeAddress = "Av. Heroínas #450 esq. Ayacucho, Plaza Principal 14 de Septiembre";
        } else {
            // Default La Paz
            lat = -16.539652;
            lng = -68.086808;
            placeCode = "FW67+66F La Paz";
            placeAddress = "Av. Rafael Pabón, Megacenter Irpavi, Zona Sur";
        }

        tvCode.setText(placeCode);
        tvAddress.setText(placeAddress);
    }

    private void setupGoogleMapFragment() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        try {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.getUiSettings().setCompassEnabled(true);
            mMap.getUiSettings().setMapToolbarEnabled(true);

            LatLng branchLocation = new LatLng(lat, lng);

            mMap.addMarker(new MarkerOptions()
                    .position(branchLocation)
                    .title("Chelo Biker Shop - " + cityName)
                    .snippet(placeAddress)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(branchLocation, 15.5f));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupButtons() {
        ImageView btnBack = findViewById(R.id.btn_map_back);
        btnBack.setOnClickListener(v -> finish());

        FrameLayout btnDirections = findViewById(R.id.btn_place_directions);
        btnDirections.setOnClickListener(v -> {
            try {
                Uri gmmIntentUri = Uri.parse("geo:" + lat + "," + lng + "?q=" + lat + "," + lng + "(" + Uri.encode("Chelo Biker Shop - " + cityName) + ")");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            } catch (Exception e) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/?api=1&query=" + lat + "," + lng));
                startActivity(webIntent);
            }
        });

        LinearLayout btnRealizarCompra = findViewById(R.id.btn_realizar_compra);
        btnRealizarCompra.setOnClickListener(v -> handleRealizarCompra());
    }

    private void handleRealizarCompra() {
        String orderId = "CHELO-" + (100000 + new Random().nextInt(900000));

        new AlertDialog.Builder(this)
                .setTitle("¡COMPRA REALIZADA CON ÉXITO! 🎉")
                .setMessage("¡Felicitaciones " + billingName + "!\n\n" +
                        "• Código de Orden: " + orderId + "\n" +
                        "• Producto: " + productTitle + " (x" + quantity + ")\n" +
                        "• Color: " + productColor + "\n" +
                        "• Total: " + productPrice + "\n" +
                        "• Método de Pago: " + payMethod + "\n" +
                        "• Sucursal de Retiro: " + cityName + " (" + placeCode + ")\n" +
                        "• Dirección: " + placeAddress + "\n\n" +
                        "¡Tu factura electrónica y orden de retiro en sucursal han sido emitidas!")
                .setPositiveButton("Finalizar e Ir al Menú", (dialog, which) -> {
                    Intent homeIntent = new Intent(BranchMapActivity.this, HomeActivity.class);
                    homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(homeIntent);
                    finish();
                })
                .show();
    }

    private void setupNavigation() {
        FrameLayout btnHome = findViewById(R.id.nav_btn_home);
        if (btnHome != null) {
            btnHome.setOnClickListener(v -> {
                Intent intent = new Intent(BranchMapActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            });
        }

        FrameLayout btnProfile = findViewById(R.id.nav_btn_profile);
        if (btnProfile != null) {
            btnProfile.setOnClickListener(v -> {
                Intent intent = new Intent(BranchMapActivity.this, ProfileActivity.class);
                startActivity(intent);
            });
        }

        FrameLayout btnMenu = findViewById(R.id.nav_btn_menu);
        if (btnMenu != null) {
            btnMenu.setOnClickListener(v -> {
                Intent intent = new Intent(BranchMapActivity.this, BranchesActivity.class);
                startActivity(intent);
            });
        }
    }
}
