package br.com.studiotrek.faculdadeimpacta.presentation.schedule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.studiotrek.faculdadeimpacta.App
import br.com.studiotrek.faculdadeimpacta.R
import br.com.studiotrek.faculdadeimpacta.dagger.`interface`.BaseDagger
import javax.inject.Inject

/**
 * Created by Kleber on 20/02/2018.
 */
class ScheduleActivity : AppCompatActivity(), SchedulePresenter.View {

    @Inject
    lateinit var presenter: SchedulePresenter
    private val TAG: String = "ScheduleActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)
        init()
    }

    private fun init() {
        (application as App).component.inject(this)
        presenter.bindView(this)
    }

    fun doRequest(cookie : String) {
        presenter.getSchedule(cookie)
    }

    override fun successRequest(horarioModel: ScheduleModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        //Apresentar dados na activity
    }

    override fun badRequest(errorMessage: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        //Apresentar dados na activity
    }

}