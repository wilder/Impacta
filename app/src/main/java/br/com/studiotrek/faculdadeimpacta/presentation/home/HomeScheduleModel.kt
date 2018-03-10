package br.com.studiotrek.faculdadeimpacta.presentation.home

import br.com.studiotrek.faculdadeimpacta.presentation.schedule.ScheduleDetailModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by kleber on 02/03/2018.
 */
class HomeScheduleModel (@SerializedName("dia") @Expose var day: String?,
                         @SerializedName("horarioDetalhado") @Expose var scheduleDetail: List<ScheduleDetailModel>?)