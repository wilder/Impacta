package br.com.studiotrek.faculdadeimpacta.dagger.component

/**
 * Created by Wilder on 18/02/18.
 */

import br.com.studiotrek.faculdadeimpacta.MainActivity
import br.com.studiotrek.faculdadeimpacta.dagger.module.MainModule
import br.com.studiotrek.faculdadeimpacta.dagger.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Wilder on 25/01/17.
 */
@Singleton
@Component(modules = [(NetworkModule::class), (MainModule::class)])
interface MainComponent {
    fun inject(activity: MainActivity)
}