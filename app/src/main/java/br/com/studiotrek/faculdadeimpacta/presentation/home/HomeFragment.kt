package br.com.studiotrek.faculdadeimpacta.presentation.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.studiotrek.faculdadeimpacta.App
import br.com.studiotrek.faculdadeimpacta.R
import br.com.studiotrek.faculdadeimpacta.domain.entity.CookieDTO
import br.com.studiotrek.faculdadeimpacta.domain.entity.home.HomeScheduleResponse
import br.com.studiotrek.faculdadeimpacta.utils.PreferencesManager
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

/**
 * Created by kleber on 02/03/2018.
 */
class HomeFragment : Fragment(), HomePresenter.View {

    @Inject
    lateinit var presenter: HomePresenter
    private val TAG: String = "HomeFragment"

    companion object {
        fun newInstance() = HomeFragment() as Fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        ((activity!!.application) as App).component.inject(this)
        presenter.bindView(this)

        pbHome.visibility = View.VISIBLE
        doRequest(PreferencesManager(context!!).cookie)
    }

    private fun setupList(classHome: HomeScheduleResponse) {
        rvHome.layoutManager = LinearLayoutManager(activity) as RecyclerView.LayoutManager?
        rvHome.adapter = HomeRecyclerViewAdapter(classHome)
    }


    fun doRequest(cookie: CookieDTO) {
        presenter.getHome(cookie)
        presenter.getHome(cookie)
    }

    override fun setUserNameAndCourse(userName: String, course: String) {
        tv_homeheader_course.text = "Hoje suas aulas no $course são:"
        tv_homeheader_name.text = "Olá, $userName"
        pbHome.visibility = View.INVISIBLE
        //TODO: update user sharedPreferences
    }

    override fun setNoClassesTodayMessage() {
        tv_homeheader_course.visibility = View.INVISIBLE
        ivHomeNoClasses.visibility = View.VISIBLE
        tvHomeWithoutClass.visibility = View.VISIBLE
        //TODO: display no classes layout
    }

    override fun displayClasses(classSchedule: HomeScheduleResponse) {
        pbHome.visibility = View.INVISIBLE
        setupList(classSchedule)
    }

    override fun badRequest(errorMessage: String) {
        //Apresentar dados na activity
        pbHome.visibility = View.INVISIBLE
    }

}