package com.example.miappbiker;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private TextView tabMotos;
    private TextView tabCascos;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        tabMotos = view.findViewById(R.id.tab_motos);
        tabCascos = view.findViewById(R.id.tab_cascos);

        tabMotos.setOnClickListener(v -> selectTab(true));
        tabCascos.setOnClickListener(v -> selectTab(false));

        // Default tab: Motos
        selectTab(true);

        return view;
    }

    private void selectTab(boolean isMotos) {
        if (isMotos) {
            tabMotos.setBackgroundResource(R.drawable.bg_pill_btn);
            tabMotos.setBackgroundTintList(android.content.res.ColorStateList.valueOf(Color.parseColor("#212121")));
            tabMotos.setTextColor(Color.WHITE);

            tabCascos.setBackgroundResource(0);
            tabCascos.setTextColor(Color.parseColor("#555555"));

            getChildFragmentManager().beginTransaction()
                    .replace(R.id.catalog_content_container, MotosFragment.newInstance())
                    .commit();
        } else {
            tabCascos.setBackgroundResource(R.drawable.bg_pill_btn);
            tabCascos.setBackgroundTintList(android.content.res.ColorStateList.valueOf(Color.parseColor("#212121")));
            tabCascos.setTextColor(Color.WHITE);

            tabMotos.setBackgroundResource(0);
            tabMotos.setTextColor(Color.parseColor("#555555"));

            getChildFragmentManager().beginTransaction()
                    .replace(R.id.catalog_content_container, CascosFragment.newInstance())
                    .commit();
        }
    }
}
