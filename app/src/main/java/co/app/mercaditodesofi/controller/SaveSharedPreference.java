package co.app.mercaditodesofi.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreference {

    private static final String PREF_USER_NAME = "username",
                                PREF_PASSWORD = "java",
                                PREF_USER_ID = "user_id",
                                PREF_ENABLED = "log_enabled";

    public static SharedPreferences getSharedPrederences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserName(Context ctx, String username) {
        SharedPreferences.Editor editor = getSharedPrederences(ctx).edit();

        editor.putString(PREF_USER_NAME, username);
        editor.commit();
    }

    public static void setPassword(Context ctx, String password) {
        SharedPreferences.Editor editor = getSharedPrederences(ctx).edit();

        editor.putString(PREF_PASSWORD, password);
        editor.commit();
    }

    public static void setUserId(Context ctx, int id) {
        SharedPreferences.Editor editor = getSharedPrederences(ctx).edit();

        editor.putString(PREF_USER_ID, String.valueOf(id));
        editor.commit();
    }

    public static String getUserName(Context ctx) {
        return getSharedPrederences(ctx).getString(PREF_USER_NAME, "");
    }

    public static String getPassword(Context ctx) {
        return getSharedPrederences(ctx).getString(PREF_PASSWORD, "");
    }

    public static int getUserId(Context ctx) {
        return Integer.valueOf(getSharedPrederences(ctx).getString(PREF_USER_ID, "0"));
    }

    public static void clear(Context ctx) {
        SharedPreferences.Editor editor = getSharedPrederences(ctx).edit();
        editor.clear();
        editor.commit();
    }

    public static boolean getEnabled(Context ctx){
        return getSharedPrederences(ctx).getBoolean(PREF_ENABLED, false);
    }

    public static void setEnabled(Context ctx, boolean enabled) {
        SharedPreferences.Editor editor = getSharedPrederences(ctx).edit();
        editor.putBoolean(PREF_ENABLED, enabled);
        editor.commit();
    }

}
