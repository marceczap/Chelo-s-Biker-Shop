package com.example.miappbiker;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocationsFragment extends Fragment implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap googleMap;

    private TextView btnCityCbba;
    private TextView btnCityLaPaz;
    private TextView btnCityElAlto;

    private TextView tvStoreCode;
    private TextView tvStoreAddress;
    private ImageView btnMapDirections;
    private ImageView btnMapShare;
    private LinearLayout btnCheckout;

    private static class BranchLocation {
        String city;
        String code;
        String address;
        double lat;
        double lng;

        BranchLocation(String city, String code, String address, double lat, double lng) {
            this.city = city;
            this.code = code;
            this.address = address;
            this.lat = lat;
            this.lng = lng;
        }
    }

    private final BranchLocation[] branches = new BranchLocation[]{
            new BranchLocation("Cochabamba", "JR3R+HCQ", "Calle Jordán y Av. Ayacucho, Cochabamba", -17.3935, -66.1570),
            new BranchLocation("La Paz", "FW67+66F", "Av. Rafael Pabón (Megacenter), La Paz", -16.5367, -68.0864),
            new BranchLocation("El Alto", "Panamericana", "Av. Panamericana y Calle 13, El Alto", -16.5132, -68.1633)
    };

    private int selectedBranchIndex = 0;

    public static LocationsFragment newInstance() {
        return new LocationsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_locations, container, false);

        mapView = view.findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        btnCityCbba = view.findViewById(R.id.btn_city_cbba);
        btnCityLaPaz = view.findViewById(R.id.btn_city_lapaz);
        btnCityElAlto = view.findViewById(R.id.btn_city_elalto);

        tvStoreCode = view.findViewById(R.id.tv_store_code);
        tvStoreAddress = view.findViewById(R.id.tv_store_address);
        btnMapDirections = view.findViewById(R.id.btn_map_directions);
        btnMapShare = view.findViewById(R.id.btn_map_share);
        btnCheckout = view.findViewById(R.id.btn_checkout);

        setupCityButtons();
        setupActions();

        return view;
    }

    private void setupCityButtons() {
        btnCityCbba.setOnClickListener(v -> selectBranch(0));
        btnCityLaPaz.setOnClickListener(v -> selectBranch(1));
        btnCityElAlto.setOnClickListener(v -> selectBranch(2));
    }

    private void selectBranch(int index) {
        selectedBranchIndex = index;
        BranchLocation branch = branches[index];

        // Update button states
        updateButtonTab(btnCityCbba, index == 0);
        updateButtonTab(btnCityLaPaz, index == 1);
        updateButtonTab(btnCityElAlto, index == 2);

        // Update info card
        tvStoreCode.setText(branch.code);
        tvStoreAddress.setText(branch.address);

        // Move Google Map
        if (googleMap != null) {
            LatLng point = new LatLng(branch.lat, branch.lng);
            googleMap.clear();
            googleMap.addMarker(new MarkerOptions()
                    .position(point)
                    .title("Chelo Biker Shop - " + branch.city)
                    .snippet(branch.address));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(point, 15f));
        }
    }

    private void updateButtonTab(TextView tv, boolean selected) {
        if (selected) {
            tv.setBackgroundResource(R.drawable.bg_pill_btn);
            tv.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#212121")));
            tv.setTextColor(Color.WHITE);
        } else {
            tv.setBackgroundResource(R.drawable.bg_pill_btn);
            tv.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E0E0E0")));
            tv.setTextColor(Color.parseColor("#333333"));
        }
    }

    private void setupActions() {
        btnMapDirections.setOnClickListener(v -> {
            BranchLocation branch = branches[selectedBranchIndex];
            Uri gmmIntentUri = Uri.parse("google.navigation:q=" + branch.lat + "," + branch.lng);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            if (mapIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
                startActivity(mapIntent);
            } else {
                Intent fallback = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/?api=1&query=" + branch.lat + "," + branch.lng));
                startActivity(fallback);
            }
        });

        btnMapShare.setOnClickListener(v -> {
            BranchLocation branch = branches[selectedBranchIndex];
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Chelo Biker Shop - Sucursal " + branch.city);
            shareIntent.putExtra(Intent.EXTRA_TEXT, "¡Visita Chelo Biker Shop en " + branch.city + "!\nDirección: " + branch.address + "\nUbicación: https://maps.google.com/?q=" + branch.lat + "," + branch.lng);
            startActivity(Intent.createChooser(shareIntent, "Compartir sucursal"));
        });

        btnCheckout.setOnClickListener(v -> {
            BranchLocation branch = branches[selectedBranchIndex];
            new AlertDialog.Builder(requireContext())
                    .setTitle("Finalizar Compra 🏁")
                    .setMessage("¿Deseas confirmar tu pedido con retiro en la sucursal de " + branch.city + " (" + branch.address + ")?")
                    .setPositiveButton("Confirmar Compra", (dialog, which) -> {
                        Toast.makeText(getContext(), "¡Compra realizada con éxito! Recibirás los detalles por WhatsApp.", Toast.LENGTH_LONG).show();
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap map) {
        this.googleMap = map;
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        selectBranch(selectedBranchIndex);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mapView != null) mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mapView != null) mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mapView != null) mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (mapView != null) mapView.onLowMemory();
    }
}
