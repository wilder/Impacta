package br.com.studiotrek.faculdadeimpacta.domain.entity.home

import br.com.studiotrek.faculdadeimpacta.domain.entity.home.HomeScheduleModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by kleber on 02/03/2018.
 */
class HomeScheduleResponse (@SerializedName("nome") @Expose var name: String,
                            @SerializedName("rm") @Expose var rm: String,
                            @SerializedName("curso") @Expose var course: String,
                            @SerializedName("horario") @Expose var homeScheduleModel: HomeScheduleModel)