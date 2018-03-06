package br.com.studiotrek.faculdadeimpacta.presentation.grades_absence

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.studiotrek.faculdadeimpacta.App
import br.com.studiotrek.faculdadeimpacta.R
import br.com.studiotrek.faculdadeimpacta.domain.entity.CookieDTO
import br.com.studiotrek.faculdadeimpacta.utils.PreferencesManager
import javax.inject.Inject

/**
 * Created by kleber on 05/03/2018.
 */
class GrandesAbsenceFragment : Fragment(), GrandesAbsencePresenter.View {

    @Inject
    lateinit var presenter: GrandesAbsencePresenter
    private val TAG: String = "GrandesAbsenceFragment"

    companion object {
        fun newInstance() = GrandesAbsenceFragment() as Fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.activity_grades_absence, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        ((activity!!.application) as App).component.inject(this)
        presenter.bindView(this)
        doRequest(PreferencesManager(context!!).cookie, "")
    }

    fun doRequest(cookie : CookieDTO, url : String) {
        presenter.getGrandesAbsence(cookie, url)
    }

    override fun successRequest(classGradesAbsence: List<GradesAbsenceResponse>) {
        Log.d(TAG, "GrandesAbsence: "+ classGradesAbsence.size)
    }

    override fun badRequest(errorMessage: String) {
        //Apresentar dados na activity
    }

}