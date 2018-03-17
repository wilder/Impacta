package br.com.studiotrek.faculdadeimpacta.dagger.module.network

import br.com.studiotrek.faculdadeimpacta.App
import br.com.studiotrek.faculdadeimpacta.domain.entity.CookieDTO
import br.com.studiotrek.faculdadeimpacta.domain.entity.Student
import br.com.studiotrek.faculdadeimpacta.utils.PreferencesManager
import java.io.IOException

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import rx.android.schedulers.AndroidSchedulers
import rx.observables.BlockingObservable


/**
 * Created by Wilder on 17/03/18.
 */

class TokenAuthenticator constructor(val apiServiceHolder: ApiServiceHolder, val app: App) : Authenticator {

    @Throws(IOException::class)
    override fun authenticate(route: Route, response: Response): Request? {

        //TODO: Inject preferences manager
        val user = PreferencesManager(app).user

        if (shouldAuthenticate(user, response) && apiServiceHolder.get() != null) {

            val loginResponse = BlockingObservable.from(apiServiceHolder.get()!!.login(user?.ra!!, user.password)).first()

            if (loginResponse != null && loginResponse.isSuccessful) {

                val cookie = loginResponse.body().cookie
                PreferencesManager(app).cookie = CookieDTO(cookie)
                // Add new header to rejected request and retry it
                return response.request().newBuilder()
                        .header("token", loginResponse.body().cookie)
                        .build()
            }

        }

        return null
    }

    private fun shouldAuthenticate(user: Student?, response: Response) =
            !user?.ra.isNullOrEmpty() && !user?.password.isNullOrEmpty()
                    && response.request().header("token") != null

}