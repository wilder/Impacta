package br.com.studiotrek.faculdadeimpacta.presentation.grades_absence

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by kleber on 06/03/2018.
 */
class GrandesAbsence (@SerializedName("MB1") @Expose var mb1: String,
                      @SerializedName("FALTAS1") @Expose var faltas1: String,
                      @SerializedName("MB2") @Expose var mb2: String,
                      @SerializedName("FALTAS2") @Expose var faltas2: String)