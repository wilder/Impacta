package br.com.studiotrek.faculdadeimpacta

import android.app.Application
import br.com.studiotrek.faculdadeimpacta.dagger.component.DaggerMainComponent
import br.com.studiotrek.faculdadeimpacta.dagger.component.MainComponent
import br.com.studiotrek.faculdadeimpacta.dagger.module.MainModule

/**
 * Created by Wilder on 18/02/18.
 */
class App : Application() {

    lateinit var component: MainComponent
        private set

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        component = DaggerMainComponent
                .builder()
                .mainModule(MainModule(this))
                .build()
    }
}