package com.dbdbdeep.modoostar.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.SharedPreferencesCompat;

public class PreferencesHelper {
    private static PreferencesHelper current;

    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public static PreferencesHelper getInstance(Context context) {
        if (current == null) {
            current = new PreferencesHelper();
        }

        current.setContext(context);
        return current;
    }

    private PreferencesHelper() {
    }

    public void setContext(Context context) {
        if (this.context != context) {
            this.context = context;

            if (preferences != null) {
                preferences = null;
            }

            preferences = PreferenceManager.getDefaultSharedPreferences(this.context);
            editor = preferences.edit();
        }
    }

    public SharedPreferences getPreferences() {
        return this.preferences;
    }

    public SharedPreferences.Editor getEditor() {
        return this.editor;
    }

    public void commit() {
        SharedPreferencesCompat.EditorCompat.getInstance().apply(this.editor);
    }
}
