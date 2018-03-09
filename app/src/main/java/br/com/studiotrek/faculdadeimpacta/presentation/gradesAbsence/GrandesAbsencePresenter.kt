package br.com.studiotrek.faculdadeimpacta.presentation.gradesAbsence

import br.com.studiotrek.faculdadeimpacta.domain.repository.ImpactaApi
import retrofit2.Retrofit
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by kleber on 05/03/2018.
 */
class GrandesAbsencePresenter @Inject constructor(
        val retrofit: Retrofit) {

    lateinit var view: GrandesAbsencePresenter.View

    fun bindView(view: GrandesAbsencePresenter.View) {
        this.view = view
    }

    fun getGrandesAbsence(gradesAbsenceRequest: GradesAbsenceRequest) {
        val api = retrofit.create(ImpactaApi::class.java)
        api.getGradesAbsence(gradesAbsenceRequest)
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

                })
    }

    interface View {
        fun successRequest(classGradesAbsence: List<GradesAbsenceResponse>)
        fun badRequest(errorMessage: String)
    }

}