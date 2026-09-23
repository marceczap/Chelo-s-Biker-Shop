package com.example.miappbiker;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF_NAME = "CheloBikerSession";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_NAME = "userName";
    private static final String KEY_EMAIL = "userEmail";
    private static final String KEY_PHONE = "userPhone";
    private static final String KEY_CITY = "userCity";
    private static final String KEY_CI = "userCI";

    private final SharedPreferences pref;
    private final SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void createLoginSession(String name, String email, String phone, String city, String ci) {
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PHONE, phone);
        editor.putString(KEY_CITY, city);
        editor.putString(KEY_CI, ci);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public String getUserName() {
        return pref.getString(KEY_NAME, "Marcelo Biker");
    }

    public String getUserEmail() {
        return pref.getString(KEY_EMAIL, "marcelo@chelobikershop.bo");
    }

    public String getUserPhone() {
        return pref.getString(KEY_PHONE, "+591 76543210");
    }

    public String getUserCity() {
        return pref.getString(KEY_CITY, "Cochabamba, Bolivia");
    }

    public String getUserCI() {
        return pref.getString(KEY_CI, "8472910 CBBA");
    }

    public void logout() {
        editor.clear();
        editor.apply();
    }
}
