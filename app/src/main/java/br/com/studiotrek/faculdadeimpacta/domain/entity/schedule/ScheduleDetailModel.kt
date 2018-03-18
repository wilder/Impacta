package br.com.studiotrek.faculdadeimpacta.domain.entity.schedule

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Kleber on 20/02/2018.
 */
@Parcelize
class ScheduleDetailModel(@SerializedName("disciplina") @Expose var className: String,
                          @SerializedName("professor") @Expose var professor: String,
                          @SerializedName("sala") @Expose var room: String) : Parcelable