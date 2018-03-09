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
class GrandesAbsenceActivity : AppCompatActivity(), GrandesAbsencePresenter.View {

    @Inject
    lateinit var presenter: GrandesAbsencePresenter
    private val TAG: String = "GrandesAbsenceActivity"

//    companion object {
//        fun newInstance() = GrandesAbsenceActivity() as Fragment
//    }

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        super.onCreateView(inflater, container, savedInstanceState)
//        return inflater.inflate(R.layout.activity_grades_absence, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        init()
//    }

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