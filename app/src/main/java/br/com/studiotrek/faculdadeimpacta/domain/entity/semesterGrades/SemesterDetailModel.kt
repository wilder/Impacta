package br.com.studiotrek.faculdadeimpacta.domain.entity.semesterGrades

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Kleber on 21/02/2018.
 */
@Parcelize
class SemesterDetailModel(@SerializedName("faculdade") @Expose var faculdade: String,
                          @SerializedName("anoSemestre") @Expose var anoSemestre: String,
                          @SerializedName("curso") @Expose var curso: String,
                          @SerializedName("horario") @Expose var horario: String,
                          @SerializedName("semestre") @Expose var semestre: String,
                          @SerializedName("status") @Expose var status: String,
                          @SerializedName("urlBoletim") @Expose var urlBoletim: String) : Parcelable