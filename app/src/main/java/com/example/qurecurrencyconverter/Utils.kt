package com.example.qurecurrencyconverter

import android.app.Activity
import android.content.Context

object Utils {
    fun Activity.saveSharedPreferenceString(key: String, value: String) {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString(key, value)
            apply()
        }
    }

    fun Activity.getStringSharedPreference(key: String) = getPreferences(Context.MODE_PRIVATE)
        .getString(key, "")

}