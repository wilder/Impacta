package br.com.studiotrek.faculdadeimpacta.presentation.semester_grades

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.studiotrek.faculdadeimpacta.App
import br.com.studiotrek.faculdadeimpacta.R
import br.com.studiotrek.faculdadeimpacta.dagger.`interface`.BaseDagger
import javax.inject.Inject

/**
 * Created by Kleber on 21/02/2018.
 */
class SemesterActivity : AppCompatActivity(), SemesterPresenter.View, BaseDagger {

    @Inject
    lateinit var presenter: SemesterPresenter
    private val TAG: String = "SemesterPresenter"

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
        presenter.getSemester(cookie)
    }

    override fun successRequest(semesterModel: SemesterModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun badRequest(errorMessage: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}