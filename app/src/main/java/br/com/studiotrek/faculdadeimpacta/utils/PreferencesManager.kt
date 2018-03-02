package br.com.studiotrek.faculdadeimpacta.utils

import android.content.Context
import android.content.SharedPreferences
import br.com.studiotrek.faculdadeimpacta.domain.entity.CookieDTO
import br.com.studiotrek.faculdadeimpacta.domain.entity.Student
import com.google.gson.Gson

/**
 * Manages dealing with SharedPreferences
 * Created by Wilder on 19/02/18.
 */
class PreferencesManager (context: Context) {

    val PREFS_FILENAME = "impactaapp.prefs"
    val USER = "user_obj"
    val COOKIE_KEY = "cookie_key"
    val INTRO_KEY = "intro_key"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var cookie: CookieDTO
        get() = Gson().fromJson(prefs.getString(COOKIE_KEY, ""), CookieDTO::class.java)
        set(value) = prefs.edit().putString(COOKIE_KEY, Gson().toJson(value)).apply()

    var user: Student?
        get() = Gson().fromJson(prefs.getString(USER, ""), Student::class.java)
        set(user) = prefs.edit().putString(USER, Gson().toJson(user)).apply()

    var haveSeenIntro: Boolean
        get() = prefs.getBoolean(INTRO_KEY, false)
        set(value) = prefs.edit().putBoolean(INTRO_KEY, value).apply()

}