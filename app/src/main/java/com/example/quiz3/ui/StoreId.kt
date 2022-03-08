package com.example.quiz3.ui

import android.content.Context
import android.content.SharedPreferences
import com.example.quiz3.R

class StoreId(context: Context) {
    private var prefs: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    fun saveId(id: String) {
        val editor = prefs.edit()
        editor.putString("user_id", id)
        editor.apply()
    }

    fun fetchId(): String? {
        return prefs.getString("user_id", null)
    }
}