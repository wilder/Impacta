package br.com.studiotrek.faculdadeimpacta.presentation.gradesAbsence

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import br.com.studiotrek.faculdadeimpacta.App
import br.com.studiotrek.faculdadeimpacta.R
import br.com.studiotrek.faculdadeimpacta.utils.PreferencesManager
import javax.inject.Inject

/**
 * Created by kleber on 05/03/2018.
 */
class GradesAbsenceActivity : AppCompatActivity(), GradesAbsencePresenter.View {

    @Inject
    lateinit var presenter: GradesAbsencePresenter
    private val TAG: String = "GradesAbsenceActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grades_absence)
        init()
    }

    private fun init() {
        (application as App).component.inject(this)
        presenter.bindView(this)

        val bundle = intent.extras
        if (bundle != null) {
            getGradesAndAbsences(PreferencesManager(this).cookie.cookie, bundle.getString("urlSemestre"))
        }
    }

    private fun getGradesAndAbsences(cookie: String, semester: String) {
        presenter.getGrandesAbsence(cookie, semester)
    }

    override fun successRequest(classGradesAbsence: GradesAbsenceResponse) {
        Log.d(TAG, "GrandesAbsence: "+ classGradesAbsence.toString())
    }

    override fun badRequest(errorMessage: String) {
        //Apresentar dados na activity
    }

}