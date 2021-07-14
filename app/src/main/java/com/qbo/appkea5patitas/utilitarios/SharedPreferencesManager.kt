package com.qbo.appkea5patitas.utilitarios

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class SharedPreferencesManager {

    private val APP_SETTINGS_FILE = "APP_SETTINGS"

    private fun getSharedPreferences(): SharedPreferences{
        return MiApp.instancia.getSharedPreferences(APP_SETTINGS_FILE,
            MODE_PRIVATE)
    }

    fun setSomeBooleanValue(clave: String, valor: Boolean){
        val editor = getSharedPreferences().edit()
        editor.putBoolean(clave, valor)
        editor.commit()
    }
    fun deletePreference(clave: String){
        getSharedPreferences().edit().remove(clave).apply()
    }
    fun getSomeBooleanValue(clave: String): Boolean{
        return getSharedPreferences().getBoolean(clave, false)
    }

    fun setSomeStringValue(clave: String, valor: String){
        val editor = getSharedPreferences().edit()
        editor.putString(clave, valor)
        editor.commit()
    }
    fun getSomeStringValue(clave: String): String{
        return getSharedPreferences().getString(clave, "").toString()
    }
}