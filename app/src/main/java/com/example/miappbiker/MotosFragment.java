package com.example.miappbiker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

public class MotosFragment extends Fragment {

    private ImageView ivMotoDisplay;
    private View btnColorBlack;
    private View btnColorRed;
    private View btnColorGreen;
    private AppCompatButton btnAddToCart;

    private int selectedColor = 0; // 0 = Black, 1 = Red, 2 = Green

    public static MotosFragment newInstance() {
        return new MotosFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_motos, container, false);

        ivMotoDisplay = view.findViewById(R.id.iv_moto_display);
        btnColorBlack = view.findViewById(R.id.btn_color_black);
        btnColorRed = view.findViewById(R.id.btn_color_red);
        btnColorGreen = view.findViewById(R.id.btn_color_green);
        btnAddToCart = view.findViewById(R.id.btn_add_to_cart);

        setupColorSelectors();

        btnAddToCart.setOnClickListener(v -> {
            String colorName = selectedColor == 0 ? "Negro" : (selectedColor == 1 ? "Rojo" : "Verde");
            Toast.makeText(getContext(), "KTM POWER 250CC (" + colorName + ") agregado al carrito 🛒", Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    private void setupColorSelectors() {
        btnColorBlack.setOnClickListener(v -> {
            selectedColor = 0;
            ivMotoDisplay.setImageResource(R.drawable.img_moto_1);
            highlightColor(btnColorBlack);
        });

        btnColorRed.setOnClickListener(v -> {
            selectedColor = 1;
            ivMotoDisplay.setImageResource(R.drawable.img_moto_2);
            highlightColor(btnColorRed);
        });

        btnColorGreen.setOnClickListener(v -> {
            selectedColor = 2;
            ivMotoDisplay.setImageResource(R.drawable.img_moto_3);
            highlightColor(btnColorGreen);
        });
    }

    private void highlightColor(View selected) {
        btnColorBlack.setScaleX(selected == btnColorBlack ? 1.15f : 1.0f);
        btnColorBlack.setScaleY(selected == btnColorBlack ? 1.15f : 1.0f);

        btnColorRed.setScaleX(selected == btnColorRed ? 1.15f : 1.0f);
        btnColorRed.setScaleY(selected == btnColorRed ? 1.15f : 1.0f);

        btnColorGreen.setScaleX(selected == btnColorGreen ? 1.15f : 1.0f);
        btnColorGreen.setScaleY(selected == btnColorGreen ? 1.15f : 1.0f);
    }
}
