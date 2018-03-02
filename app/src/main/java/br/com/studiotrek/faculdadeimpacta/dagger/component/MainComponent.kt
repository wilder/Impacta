package br.com.studiotrek.faculdadeimpacta.dagger.component

/**
 * Created by Wilder on 18/02/18.
 */

import br.com.studiotrek.faculdadeimpacta.dagger.module.MainModule
import br.com.studiotrek.faculdadeimpacta.dagger.module.NetworkModule
import br.com.studiotrek.faculdadeimpacta.presentation.home.HomeActivity
import br.com.studiotrek.faculdadeimpacta.presentation.login.LoginActivity
import br.com.studiotrek.faculdadeimpacta.presentation.menu.MenuActivity
import br.com.studiotrek.faculdadeimpacta.presentation.schedule.ScheduleActivity
import br.com.studiotrek.faculdadeimpacta.presentation.semester_grades.SemesterActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Wilder on 25/01/17.
 */
@Singleton
@Component(modules = [(NetworkModule::class), (MainModule::class)])
interface MainComponent {
    fun inject(activity: LoginActivity)
    fun inject(activity: ScheduleActivity)
    fun inject(activity: SemesterActivity)
    fun inject(activity: MenuActivity)
    fun inject(activity: HomeActivity)
}