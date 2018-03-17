package br.com.studiotrek.faculdadeimpacta.utils

import android.app.Activity
import android.view.inputmethod.InputMethodManager

/**
 * Created by Wilder on 17/03/18.
 */

fun Activity.hideSoftKeyboard() {
    val inputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.currentFocus!!.windowToken, 0)
}
