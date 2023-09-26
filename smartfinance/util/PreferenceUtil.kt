package pemberkel9.smartfinance.util

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor

object PreferenceUtil {

    private var context: Context? = null
    val NAME = "app_preference"
    val SETUP_REGISTER = "register"
    val SETUP_LOGIN = "login"
    val SALDO = "saldo_masuk"
    val UANG_MASUK = "uang_masuk"
    val UANG_KELUAR = "uang_keluar"

    fun getPref(context: Context): SharedPreferences? {
        return context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
    }

    fun getEditor(context: Context): Editor? {
        return context.getSharedPreferences(NAME, Context.MODE_PRIVATE).edit()
    }

}