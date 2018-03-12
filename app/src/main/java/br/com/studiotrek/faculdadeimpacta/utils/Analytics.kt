package br.com.studiotrek.faculdadeimpacta.utils

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics


/**
 * Created by Wilder on 11/03/18.
 */
object Analytics {

    fun logBadEvent(firebaseAnalytics: FirebaseAnalytics, user: String, event: String) {
        val params = Bundle()
        params.putString("user", user)
        params.putString("event", event)
        firebaseAnalytics.logEvent("bad_event", params)
    }

    fun logLogin(firebaseAnalytics: FirebaseAnalytics, user: String) {
        val params = Bundle()
        params.putString("user", user)
        firebaseAnalytics.logEvent("login_event", params)
    }

    fun logVisit(firebaseAnalytics: FirebaseAnalytics, user: String, pageName: String) {
        val params = Bundle()
        params.putString("user", user)
        params.putString("screen", pageName)
        firebaseAnalytics.logEvent("visit_event", params)
    }

}