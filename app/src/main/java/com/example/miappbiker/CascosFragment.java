package com.example.miappbiker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

public class CascosFragment extends Fragment {

    private TextView tvCascoTitle;
    private ImageView ivCascoDisplay;
    private TextView tvCascoPrice;
    private TextView tvHelmetCount;
    private AppCompatButton btnPrev;
    private AppCompatButton btnNext;
    private AppCompatButton btnAddToCart;

    private static class HelmetModel {
        String title;
        int imageRes;
        String price;

        HelmetModel(String title, int imageRes, String price) {
            this.title = title;
            this.imageRes = imageRes;
            this.price = price;
        }
    }

    private final HelmetModel[] helmets = new HelmetModel[]{
            new HelmetModel("Casco O´neal tipo racer", R.drawable.img_helmet_orange, "590 bs.-"),
            new HelmetModel("Casco Blauer Integral", R.drawable.img_casco, "900 bs.-"),
            new HelmetModel("Casco Aviator 2.3 Off-Road", R.drawable.img_helmet_orange, "1589 bs.-")
    };

    private int currentIndex = 0;

    public static CascosFragment newInstance() {
        return new CascosFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cascos, container, false);

        tvCascoTitle = view.findViewById(R.id.tv_casco_title);
        ivCascoDisplay = view.findViewById(R.id.iv_casco_display);
        tvCascoPrice = view.findViewById(R.id.tv_casco_price);
        tvHelmetCount = view.findViewById(R.id.tv_helmet_count);
        btnPrev = view.findViewById(R.id.btn_prev_helmet);
        btnNext = view.findViewById(R.id.btn_next_helmet);
        btnAddToCart = view.findViewById(R.id.btn_add_casco_to_cart);

        updateHelmetUI();

        btnPrev.setOnClickListener(v -> {
            if (currentIndex > 0) {
                currentIndex--;
            } else {
                currentIndex = helmets.length - 1;
            }
            updateHelmetUI();
        });

        btnNext.setOnClickListener(v -> {
            if (currentIndex < helmets.length - 1) {
                currentIndex++;
            } else {
                currentIndex = 0;
            }
            updateHelmetUI();
        });

        btnAddToCart.setOnClickListener(v -> {
            Toast.makeText(getContext(), helmets[currentIndex].title + " (" + helmets[currentIndex].price + ") agregado al carrito 🛒", Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    private void updateHelmetUI() {
        HelmetModel current = helmets[currentIndex];
        tvCascoTitle.setText(current.title);
        ivCascoDisplay.setImageResource(current.imageRes);
        tvCascoPrice.setText(current.price);
        tvHelmetCount.setText("Modelo " + (currentIndex + 1) + " de " + helmets.length);
    }
}
