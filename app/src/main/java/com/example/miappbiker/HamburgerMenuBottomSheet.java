package com.example.miappbiker;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.core.os.LocaleListCompat;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class HamburgerMenuBottomSheet extends BottomSheetDialogFragment {

    public static final String TAG = "HamburgerMenuBottomSheet";

    private SessionManager sessionManager;
    private LinearLayout btnLangEs;
    private LinearLayout btnLangEn;
    private TextView tvLangEs;
    private TextView tvLangEn;

    public static void showMenu(FragmentManager fragmentManager) {
        HamburgerMenuBottomSheet bottomSheet = new HamburgerMenuBottomSheet();
        bottomSheet.show(fragmentManager, TAG);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottom_sheet_hamburger_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sessionManager = new SessionManager(requireContext());

        TextView tvUserInfo = view.findViewById(R.id.menu_user_info);
        if (tvUserInfo != null) {
            String name = sessionManager.getUserName();
            String email = sessionManager.getUserEmail();
            tvUserInfo.setText(name + " • " + email);
        }

        CardView btnContact = view.findViewById(R.id.menu_btn_contact);
        if (btnContact != null) {
            btnContact.setOnClickListener(v -> showContactDialog());
        }

        CardView btnLegal = view.findViewById(R.id.menu_btn_legal);
        if (btnLegal != null) {
            btnLegal.setOnClickListener(v -> showLegalDialog());
        }

        btnLangEs = view.findViewById(R.id.btn_lang_es);
        btnLangEn = view.findViewById(R.id.btn_lang_en);
        tvLangEs = view.findViewById(R.id.tv_lang_es);
        tvLangEn = view.findViewById(R.id.tv_lang_en);

        updateLanguageButtonsUI(sessionManager.getLanguage());

        if (btnLangEs != null) {
            btnLangEs.setOnClickListener(v -> changeAppLanguage("es"));
        }

        if (btnLangEn != null) {
            btnLangEn.setOnClickListener(v -> changeAppLanguage("en"));
        }

        CardView btnLogout = view.findViewById(R.id.menu_btn_logout);
        if (btnLogout != null) {
            btnLogout.setOnClickListener(v -> showLogoutDialog());
        }
    }

    private void updateLanguageButtonsUI(String lang) {
        if (btnLangEs == null || btnLangEn == null) return;

        if ("en".equalsIgnoreCase(lang)) {
            btnLangEn.setBackgroundResource(R.drawable.bg_lang_selected);
            tvLangEn.setTextColor(0xFFFFFFFF);

            btnLangEs.setBackgroundResource(R.drawable.bg_lang_unselected);
            tvLangEs.setTextColor(0xFF4B5563);
        } else {
            btnLangEs.setBackgroundResource(R.drawable.bg_lang_selected);
            tvLangEs.setTextColor(0xFFFFFFFF);

            btnLangEn.setBackgroundResource(R.drawable.bg_lang_unselected);
            tvLangEn.setTextColor(0xFF4B5563);
        }
    }

    private void changeAppLanguage(String langCode) {
        sessionManager.setLanguage(langCode);
        updateLanguageButtonsUI(langCode);

        // Apply locale system-wide
        LocaleListCompat appLocale = LocaleListCompat.forLanguageTags(langCode);
        AppCompatDelegate.setApplicationLocales(appLocale);

        String msg = "en".equalsIgnoreCase(langCode) ? "Language changed to English 🇺🇸" : "Idioma cambiado a Español 🇪🇸";
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();

        dismiss();
        if (getActivity() != null) {
            getActivity().recreate();
        }
    }

    private void showContactDialog() {
        if (getContext() == null) return;

        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_contact_us, null);
        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setView(dialogView)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }

        CardView btnWhatsapp = dialogView.findViewById(R.id.btn_contact_whatsapp);
        if (btnWhatsapp != null) {
            btnWhatsapp.setOnClickListener(v -> {
                try {
                    String url = "https://api.whatsapp.com/send?phone=+59176543210&text=Hola%20Chelo%20Biker%20Shop%20quisiera%20mas%20informacion";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "WhatsApp: +591 76543210 💬", Toast.LENGTH_LONG).show();
                }
            });
        }

        CardView btnCall = dialogView.findViewById(R.id.btn_contact_call);
        if (btnCall != null) {
            btnCall.setOnClickListener(v -> {
                try {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:+59176543210"));
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Teléfono: +591 76543210 📞", Toast.LENGTH_LONG).show();
                }
            });
        }

        CardView btnEmail = dialogView.findViewById(R.id.btn_contact_email);
        if (btnEmail != null) {
            btnEmail.setOnClickListener(v -> {
                try {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:contacto@chelobiker.com"));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Consulta Chelo Biker Shop");
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Email: contacto@chelobiker.com ✉️", Toast.LENGTH_LONG).show();
                }
            });
        }

        AppCompatButton btnClose = dialogView.findViewById(R.id.btn_close_contact);
        if (btnClose != null) {
            btnClose.setOnClickListener(v -> dialog.dismiss());
        }

        dialog.show();
    }

    private void showLegalDialog() {
        if (getContext() == null) return;

        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_legal_info, null);
        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setView(dialogView)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }

        AppCompatButton btnClose = dialogView.findViewById(R.id.btn_close_legal);
        if (btnClose != null) {
            btnClose.setOnClickListener(v -> dialog.dismiss());
        }

        dialog.show();
    }

    private void showLogoutDialog() {
        if (getContext() == null) return;

        new AlertDialog.Builder(getContext())
                .setTitle(R.string.menu_logout)
                .setMessage(R.string.logout_confirm_msg)
                .setPositiveButton(R.string.logout_yes, (dialog, which) -> {
                    sessionManager.logout();
                    Toast.makeText(getContext(), "Sesión cerrada con éxito 👋", Toast.LENGTH_SHORT).show();
                    dismiss();
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                })
                .setNegativeButton(R.string.logout_no, null)
                .show();
    }
}
