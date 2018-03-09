package br.com.studiotrek.faculdadeimpacta.dagger.component

/**
 * Created by Wilder on 18/02/18.
 */

import br.com.studiotrek.faculdadeimpacta.dagger.module.MainModule
import br.com.studiotrek.faculdadeimpacta.dagger.module.NetworkModule
import br.com.studiotrek.faculdadeimpacta.presentation.gradesAbsence.GrandesAbsenceActivity
import br.com.studiotrek.faculdadeimpacta.presentation.home.HomeFragment
import br.com.studiotrek.faculdadeimpacta.presentation.login.LoginActivity
import br.com.studiotrek.faculdadeimpacta.presentation.menu.MenuActivity
import br.com.studiotrek.faculdadeimpacta.presentation.schedule.ScheduleFragment
import br.com.studiotrek.faculdadeimpacta.presentation.semesterGrades.SemesterFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Wilder on 25/01/17.
 */
@Singleton
@Component(modules = [(NetworkModule::class), (MainModule::class)])
interface MainComponent {
    fun inject(activity: LoginActivity)
    fun inject(fragment: ScheduleFragment)
    fun inject(fragment: SemesterFragment)
    fun inject(activity: MenuActivity)
    fun inject(fragment: HomeFragment)
    fun inject(fragment: GrandesAbsenceActivity)
}