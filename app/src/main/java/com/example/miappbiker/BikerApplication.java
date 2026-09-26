package com.example.miappbiker;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;

/**
 * Inicialización global de la aplicación Chelo's Biker Shop.
 * Aplica el tema (Modo Claro / Modo Oscuro) y el idioma guardados
 * antes de inflar cualquier Activity para evitar parpadeos visuales.
 */
public class BikerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SessionManager sessionManager = new SessionManager(this);

        // Aplicar Modo Oscuro / Claro
        sessionManager.applyTheme();

        // Aplicar Idioma persistido
        String lang = sessionManager.getLanguage();
        if (lang != null && !lang.isEmpty()) {
            LocaleListCompat appLocale = LocaleListCompat.forLanguageTags(lang);
            AppCompatDelegate.setApplicationLocales(appLocale);
        }
    }
}
