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

            tvName.setText(item.name);

            GradientDrawable dotDrawable = new GradientDrawable();
            dotDrawable.setShape(GradientDrawable.OVAL);
            dotDrawable.setColor(item.colorHex);
            if (item.colorHex == 0xFFFFFFFF || item.colorHex == 0xFFF9FAFB) {
                dotDrawable.setStroke(2, 0xFFCCCCCC);
            }
            vDot.setBackground(dotDrawable);
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

            tvName.setText(item.name);

            GradientDrawable dotDrawable = new GradientDrawable();
            dotDrawable.setShape(GradientDrawable.OVAL);
            dotDrawable.setColor(item.colorHex);
            if (item.colorHex == 0xFFFFFFFF || item.colorHex == 0xFFF9FAFB) {
                dotDrawable.setStroke(2, 0xFFCCCCCC);
            }
            vDot.setBackground(dotDrawable);
        }

        return convertView;
    }
}
