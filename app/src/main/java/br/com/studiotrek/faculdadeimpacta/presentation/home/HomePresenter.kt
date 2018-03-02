package br.com.studiotrek.faculdadeimpacta.presentation.home

import br.com.studiotrek.faculdadeimpacta.domain.entity.CookieDTO
import br.com.studiotrek.faculdadeimpacta.domain.repository.ImpactaApi
import br.com.studiotrek.faculdadeimpacta.presentation.schedule.ScheduleResponse
import retrofit2.Retrofit
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by kleber on 02/03/2018.
 */
class HomePresenter @Inject constructor(
        val retrofit: Retrofit) {

    lateinit var view: View

    fun bindView(view: View) {
        this.view = view
    }

    fun getHome(cookie: CookieDTO) {
        val api = retrofit.create(ImpactaApi::class.java)
        api.getHome(cookie)
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
        fun successRequest(classSchedule: HomeScheduleResponse)
        fun badRequest(errorMessage: String)
    }
}