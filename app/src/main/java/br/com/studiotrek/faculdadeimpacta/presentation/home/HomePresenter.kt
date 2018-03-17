package br.com.studiotrek.faculdadeimpacta.presentation.home

import android.util.Log
import br.com.studiotrek.faculdadeimpacta.domain.entity.CookieDTO
import br.com.studiotrek.faculdadeimpacta.domain.entity.home.HomeScheduleResponse
import br.com.studiotrek.faculdadeimpacta.domain.repository.ImpactaApi
import retrofit2.Retrofit
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by kleber on 02/03/2018.
 */
class HomePresenter @Inject constructor(
        val api: ImpactaApi) {

    lateinit var view: View

    fun bindView(view: View) {
        this.view = view
    }

    fun getHome(cookie: CookieDTO) {
        api.getHome(cookie.cookie)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.code() == 401) {
                        view.badRequest("Login expirado")
                    } else if (it.code() == 500) {
                        view.badRequest("Não foi possível buscar as aulas.\nTente novamente mais tarde.")
                    } else {
                        val classSchedule = it.body()

                        view.setUserNameAndCourse(classSchedule.name, classSchedule.course)

                        if (classSchedule.homeScheduleModel == null
                                || classSchedule.homeScheduleModel.scheduleDetail == null
                                || classSchedule.homeScheduleModel.scheduleDetail?.size == 0) {
                            view.setNoClassesTodayMessage()
                        } else {
                            view.displayClasses(it.body())
                        }
                    }
                },{
                    Log.e("HomePresenter: ",it.message)
                    //view.badRequest()
                })
    }

    interface View {
        fun setUserNameAndCourse(userName: String, course: String)
        fun setNoClassesTodayMessage()
        fun displayClasses(classSchedule: HomeScheduleResponse)
        fun badRequest(errorMessage: String)
    }
}