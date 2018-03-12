package br.com.studiotrek.faculdadeimpacta.domain.entity.home

import br.com.studiotrek.faculdadeimpacta.domain.entity.schedule.ScheduleDetailModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by kleber on 02/03/2018.
 */
class HomeScheduleModel (@SerializedName("dia") @Expose var day: String?,
                         @SerializedName("horarioDetalhado") @Expose var scheduleDetail: List<ScheduleDetailModel>?)