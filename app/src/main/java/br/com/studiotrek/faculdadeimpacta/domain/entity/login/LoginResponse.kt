package br.com.studiotrek.faculdadeimpacta.domain.entity.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Wilder on 18/02/18.
 */
class LoginResponse(@SerializedName("cookin") @Expose var cookie: String,
                    @SerializedName("error") @Expose var error: String)