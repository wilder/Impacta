package br.com.studiotrek.faculdadeimpacta.presentation.gradesAbsence

import android.util.Log
import br.com.studiotrek.faculdadeimpacta.domain.entity.gradesAbsence.GradesAbsenceRequest
import br.com.studiotrek.faculdadeimpacta.domain.entity.gradesAbsence.GradesAbsenceResponse
import br.com.studiotrek.faculdadeimpacta.domain.repository.ImpactaApi
import retrofit2.Retrofit
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by kleber on 05/03/2018.
 */
class GradesAbsencePresenter @Inject constructor(
        val api: ImpactaApi) {

    lateinit var view: GradesAbsencePresenter.View

    fun bindView(view: GradesAbsencePresenter.View) {
        this.view = view
    }

    fun getGrandesAbsence(cookie: String, semester: String) {
        api.getGradesAbsence(cookie, GradesAbsenceRequest(semester))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.code() == 401) {
                        view.badRequest("Login expirado")
                    } else if (it.code() == 500) {
                        view.badRequest("Não foi possível buscar as aulas.\nTente novamente mais tarde.")
                    } else {
                        view.successRequest(it.body())
                    }
                },{
                    Log.e("GradesAbsence: ",it.message)
                    view.badRequest("Não foi possível buscar as aulas.\nTente novamente mais tarde.")
                })
    }

    interface View {
        fun successRequest(classGradesAbsence: GradesAbsenceResponse)
        fun badRequest(errorMessage: String)
    }

}