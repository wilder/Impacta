package br.com.studiotrek.faculdadeimpacta.presentation.schedule

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Kleber on 20/02/2018.
 */
class ScheduleDetailModel(@SerializedName("disciplina") @Expose var className: String,
                          @SerializedName("professor") @Expose var professor: String,
                          @SerializedName("sala") @Expose var room: String)