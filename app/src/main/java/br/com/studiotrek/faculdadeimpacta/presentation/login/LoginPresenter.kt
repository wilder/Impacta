package br.com.studiotrek.faculdadeimpacta.presentation.login

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
                            //success
                            saveUserInfo(it.cookie, ra, password)
                            view.successfulLogin()
                        },
                        //TODO: handle status codes
                        { view.badLogin(it.message.toString())}) //error

    }

    private fun saveUserInfo(cookie: String, ra: String, password: String) {
        //TODO: save user information to shared preferences
    }

    interface View{
        fun successfulLogin()
        fun badLogin(errorMessage: String)
    }
}