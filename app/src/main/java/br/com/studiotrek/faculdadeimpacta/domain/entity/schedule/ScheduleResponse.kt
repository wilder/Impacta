package br.com.studiotrek.faculdadeimpacta.domain.entity.schedule

import br.com.studiotrek.faculdadeimpacta.domain.entity.schedule.ScheduleDetailModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Kleber on 20/02/2018.
 */
class ScheduleResponse(@SerializedName("dia") @Expose var day: String,
                       @SerializedName("horarioDetalhado") @Expose var datailedSchedule: List<ScheduleDetailModel>)