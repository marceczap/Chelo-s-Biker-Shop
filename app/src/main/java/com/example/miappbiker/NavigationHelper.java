package com.example.miappbiker;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Helper centralizado y estandarizado para la gestión de navegación, barras de control
 * y botones reutilizables en toda la aplicación Chelo's Biker Shop.
 */
public class NavigationHelper {

    /**
     * Configura automáticamente todos los componentes de navegación estándar presentes
     * en el layout de la actividad (Barra inferior, Botón de Carrito flotante, Botones de Atrás).
     */
    public static void setupAll(AppCompatActivity activity) {
        setupBottomNav(activity);
        setupFloatingCart(activity);
        setupStandardBackButtons(activity);
        updateCartBadge(activity);
    }

    /**
     * Vincula los botones de la barra de navegación inferior (Perfil, Home, Menú/Sucursales).
     */
    public static void setupBottomNav(AppCompatActivity activity) {
        // Botón Home
        View btnHome = activity.findViewById(R.id.nav_btn_home);
        if (btnHome != null) {
            btnHome.setOnClickListener(v -> {
                if (!(activity instanceof HomeActivity)) {
                    Intent intent = new Intent(activity, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    activity.startActivity(intent);
                    activity.finish();
                }
            });
        }

        // Botón Perfil
        View btnProfile = activity.findViewById(R.id.nav_btn_profile);
        if (btnProfile != null) {
            btnProfile.setOnClickListener(v -> {
                if (!(activity instanceof ProfileActivity)) {
                    Intent intent = new Intent(activity, ProfileActivity.class);
                    activity.startActivity(intent);
                }
            });
        }

        // Botón Menú Hamburguesa / Sucursales
        View btnMenu = activity.findViewById(R.id.nav_btn_menu);
        if (btnMenu != null) {
            btnMenu.setOnClickListener(v -> HamburgerMenuBottomSheet.showMenu(activity.getSupportFragmentManager()));
        }
    }

    /**
     * Vincula el botón flotante del carrito de compras y su badge contador.
     */
    public static void setupFloatingCart(AppCompatActivity activity) {
        View btnCart = activity.findViewById(R.id.btn_floating_cart);
        if (btnCart != null) {
            btnCart.setOnClickListener(v -> {
                if (!(activity instanceof CartActivity)) {
                    Intent intent = new Intent(activity, CartActivity.class);
                    activity.startActivity(intent);
                }
            });
        }
        updateCartBadge(activity);
    }

    /**
     * Actualiza el badge numérico del carrito con el total del CartManager.
     */
    public static void updateCartBadge(AppCompatActivity activity) {
        TextView tvBadge = activity.findViewById(R.id.tv_cart_badge);
        if (tvBadge != null) {
            int count = CartManager.getInstance().getTotalCount();
            tvBadge.setText(String.valueOf(count));
        }
    }

    /**
     * Vincula cualquier botón de retroceso (Volver) estándar en la actividad.
     */
    public static void setupStandardBackButtons(AppCompatActivity activity) {
        int[] backButtonIds = new int[]{
                R.id.btn_back_home,
                R.id.btn_detail_back,
                R.id.btn_cart_back,
                R.id.btn_branches_back
        };

        for (int id : backButtonIds) {
            View btn = activity.findViewById(id);
            if (btn != null) {
                btn.setOnClickListener(v -> activity.finish());
            }
        }
    }

    /**
     * Vincula un botón de volver específico.
     */
    public static void setupBackButton(AppCompatActivity activity, int viewId) {
        View btn = activity.findViewById(viewId);
        if (btn != null) {
            btn.setOnClickListener(v -> activity.finish());
        }
    }
}
