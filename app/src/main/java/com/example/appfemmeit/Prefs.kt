package com.example.appfemmeit

import android.content.SharedPreferences

object Prefs {

    val PREF_ID = "PERFIL"

    private fun prefs(): SharedPreferences {
        val context = PERFILApplication.getInstance().applicationContext
        return context.getSharedPreferences(PREF_ID, 0)

    }

    fun setBoolean(flag: String, valor: Boolean) = prefs().edit().putBoolean(flag, valor).apply()

    fun getBoolean(flag: String) = prefs().getBoolean(flag, false)

    fun setString(flag: String, valor: String) = prefs().edit().putString(flag, valor).apply()

    fun getStrin(flag: String) = prefs().getString(flag, "")

//    fun setInt(flag: Int, valor: Int) = prefs().edit().putInt(flag, valor).apply()
//
//    fun getInt(flag: Int) = prefs().getInt(flag,0)

}