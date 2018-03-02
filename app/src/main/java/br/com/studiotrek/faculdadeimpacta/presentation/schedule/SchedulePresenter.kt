package br.com.studiotrek.faculdadeimpacta.presentation.schedule

import br.com.studiotrek.faculdadeimpacta.domain.entity.CookieDTO
import br.com.studiotrek.faculdadeimpacta.domain.repository.ImpactaApi
import retrofit2.Retrofit
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Kleber on 20/02/2018.
 */
class SchedulePresenter @Inject constructor(
        val retrofit: Retrofit) {

    lateinit var view: View

    fun bindView(view: View) {
        this.view = view
    }

    fun getSchedule(cookie : String) {
        val api = retrofit.create(ImpactaApi::class.java)
        api.getClassesSchedule(CookieDTO(cookie))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if(it.code() == 401) {
                        view.badRequest("Login expirado")
                    } else if (it.code() == 500) {
                        view.badRequest("Não foi possível buscar as aulas.\nTente novamente mais tarde.")
                    } else {
                        view.successRequest(it.body())
                    }

                })
    }

    interface View{
        fun successRequest(classSchedule: List<ScheduleResponse>)
        fun badRequest(errorMessage: String)
    }

}