package com.example.miappbiker;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ColorSpinnerAdapter extends ArrayAdapter<ColorSpinnerAdapter.ColorOption> {

    public static class ColorOption {
        public String name;
        public int colorHex;

        public ColorOption(String name, int colorHex) {
            this.name = name;
            this.colorHex = colorHex;
        }

        @NonNull
        @Override
        public String toString() {
            return name;
        }
    }

    private final Context context;
    private final List<ColorOption> options;

    public ColorSpinnerAdapter(@NonNull Context context, @NonNull List<ColorOption> options) {
        super(context, R.layout.item_color_spinner, options);
        this.context = context;
        this.options = options;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_color_spinner, parent, false);
        }

        ColorOption item = getItem(position);
        if (item != null) {
            View vDot = convertView.findViewById(R.id.v_color_dot);
            TextView tvName = convertView.findViewById(R.id.tv_color_name);

            if (tvName != null) {
                tvName.setText(item.name);
                tvName.setTextColor(0xFF111111);
            }

            if (vDot != null) {
                vDot.setBackgroundTintList(null);
                GradientDrawable dotDrawable = new GradientDrawable();
                dotDrawable.setShape(GradientDrawable.OVAL);
                dotDrawable.setColor(item.colorHex);
                if (isLightColor(item.colorHex)) {
                    dotDrawable.setStroke(3, 0xFF9CA3AF);
                } else {
                    dotDrawable.setStroke(2, 0x40000000);
                }
                vDot.setBackground(dotDrawable);
            }
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_color_spinner_dropdown, parent, false);
        }

        ColorOption item = getItem(position);
        if (item != null) {
            View vDot = convertView.findViewById(R.id.v_dropdown_color_dot);
            TextView tvName = convertView.findViewById(R.id.tv_dropdown_color_name);

            if (tvName != null) {
                tvName.setText(item.name);
                tvName.setTextColor(0xFF111111);
            }

            if (vDot != null) {
                vDot.setBackgroundTintList(null);
                GradientDrawable dotDrawable = new GradientDrawable();
                dotDrawable.setShape(GradientDrawable.OVAL);
                dotDrawable.setColor(item.colorHex);
                if (isLightColor(item.colorHex)) {
                    dotDrawable.setStroke(3, 0xFF9CA3AF);
                } else {
                    dotDrawable.setStroke(2, 0x40000000);
                }
                vDot.setBackground(dotDrawable);
            }
        }

        return convertView;
    }

    private boolean isLightColor(int color) {
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = color & 0xFF;
        double brightness = (0.299 * r + 0.587 * g + 0.114 * b);
        return brightness > 180;
    }
}
