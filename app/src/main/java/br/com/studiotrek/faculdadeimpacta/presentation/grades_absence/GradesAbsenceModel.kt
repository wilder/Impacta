package br.com.studiotrek.faculdadeimpacta.presentation.grades_absence

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by kleber on 05/03/2018.
 */
class GradesAbsenceModel (@SerializedName("nomeMateria") @Expose var nomeMateria: String,
                          @SerializedName("json") @Expose var json: GrandesAbsence)