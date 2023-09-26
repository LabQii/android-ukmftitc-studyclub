package pemberkel9.smartfinance.util

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import pemberkel9.smartfinance.pojo.User
import com.google.gson.Gson

object SessionManager {

    var gson = Gson()

    @SuppressLint("SuspiciousIndentation")
    fun saveProfile(context: Context?, user: User): User{

        var updated: User? = getProfile(context)

        if (updated == null)
            updated =user
        else
            if (user.fullname != null)
                updated.fullname = user.fullname

            if (user.email != null)
                updated.email = user.email

            if (user.password != null)
                updated.password = user.password


        val json: String = gson.toJson(updated, User::class.java)
        PreferenceUtil.getEditor(context!!)!!.putString("user", json).commit()
        return updated

    }

    fun getProfile(context: Context?): User?{
        val json: String? = context?.let { PreferenceUtil.getPref(it)?.getString("user", null).toString() }
        if (!TextUtils.isEmpty(json))
            return gson.fromJson(json, User::class.java)
        else
            return null
    }
}

