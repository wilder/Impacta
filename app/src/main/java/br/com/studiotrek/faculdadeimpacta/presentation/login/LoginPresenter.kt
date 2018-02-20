package br.com.studiotrek.faculdadeimpacta.presentation.login

import br.com.studiotrek.faculdadeimpacta.domain.entity.Student
import br.com.studiotrek.faculdadeimpacta.domain.repository.ImpactaApi
import retrofit2.Retrofit
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Wilder on 18/02/18.
 */

class LoginPresenter @Inject constructor(
        val retrofit: Retrofit){

    lateinit var view: View

    fun bindView(view: View) {
        this.view = view
    }

    fun login(ra: String, password: String) {

        val api = retrofit.create(ImpactaApi::class.java)
        api.login(ra, password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            if(it.code() == 401) {
                                view.badLogin("Credenciais Inválidas")
                            } else if (it.code() == 500) {
                                //TODO: Go to some activity saying: "App in maintenance" or something like that
                                view.badLogin("Site da Impacta com problemas :/")
                            } else {
                                //success
                                view.successfulLogin(it.body().cookie)
                            }
                        },
                        //TODO: handle status codes
                        { view.badLogin(it.message.toString())}) //error

    }

    interface View{
        fun successfulLogin(cookie: String)
        fun badLogin(errorMessage: String)
    }
}