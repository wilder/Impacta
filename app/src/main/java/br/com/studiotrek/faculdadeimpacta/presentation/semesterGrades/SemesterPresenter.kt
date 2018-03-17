package br.com.studiotrek.faculdadeimpacta.presentation.semesterGrades

import android.util.Log
import br.com.studiotrek.faculdadeimpacta.domain.entity.CookieDTO
import br.com.studiotrek.faculdadeimpacta.domain.entity.semesterGrades.SemesterResponse
import br.com.studiotrek.faculdadeimpacta.domain.repository.ImpactaApi
import retrofit2.Retrofit
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Kleber on 21/02/2018.
 */
class SemesterPresenter @Inject constructor(
        val api: ImpactaApi) {

    lateinit var view: View

    fun bindView(view: View) {
        this.view = view
    }

    fun getSemester(cookie : CookieDTO) {
        api.getSemesterGrades(cookie)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if(it.code() == 401) {
                        view.badRequest("Login expirado")
                    } else if (it.code() == 500) {
                        view.badRequest("Erro interno")
                    } else {
                        view.successRequest(it.body())
                    }

                },{
                    Log.e("SemesterPresenter: ",it.message)
                    //view.badRequest()
                })
    }

    interface View{
        fun successRequest(semesterResponse: SemesterResponse)
        fun badRequest(errorMessage: String)
    }
}