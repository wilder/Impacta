package br.com.studiotrek.faculdadeimpacta.domain.entity.semesterGrades

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Kleber on 21/02/2018.
 */
@Parcelize
class SemesterResponse (@SerializedName("nomeAluno") @Expose var nomeAluno: String,
                        @SerializedName("rmAluno") @Expose var rmAluno: String,
                        @SerializedName("curso") @Expose var curso: String,
                        @SerializedName("semestres") @Expose var semesterModel: List<SemesterDetailModel>) : Parcelable