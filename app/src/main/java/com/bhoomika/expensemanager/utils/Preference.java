package com.bhoomika.expensemanager.utils;

import android.content.Context;
import android.content.SharedPreferences;


public class Preference {
    private final static String SP_NAME = "com.expensemanager";
    private static Preference preference;
    private SharedPreferences sp;

    public Preference(Context mContext) {
        sp = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public static Preference getInstance(Context mContext) {
        if (preference == null) {
            preference = new Preference(mContext);
        }
        return preference;
    }

    public static void removeInstance() {
        preference = null;
    }

    public void clearAllPrefs() {
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

    public String getPassword() {
        return sp.getString(Constant.PASSWORD, "");
    }

    public void setPassword(String password) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(Constant.PASSWORD, password);
        editor.apply();
    }


}
