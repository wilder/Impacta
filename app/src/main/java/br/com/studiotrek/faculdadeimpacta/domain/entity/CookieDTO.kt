package br.com.studiotrek.faculdadeimpacta.domain.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Wilder on 01/03/18.
 */
data class CookieDTO(@SerializedName("cookie") @Expose var cookie: String)