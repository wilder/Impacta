package br.com.studiotrek.faculdadeimpacta.presentation.gradesAbsence

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by kleber on 05/03/2018.
 */
class GradesAbsenceModel (@SerializedName("nomeMateria") @Expose var nomeMateria: String,
                          @SerializedName("falta") @Expose var json: GrandesAbsence)