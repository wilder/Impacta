package br.com.studiotrek.faculdadeimpacta.presentation.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import br.com.studiotrek.faculdadeimpacta.App
import br.com.studiotrek.faculdadeimpacta.R
import br.com.studiotrek.faculdadeimpacta.domain.entity.CookieDTO
import br.com.studiotrek.faculdadeimpacta.presentation.schedule.ScheduleResponse
import br.com.studiotrek.faculdadeimpacta.utils.PreferencesManager
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

/**
 * Created by kleber on 02/03/2018.
 */
class HomeActivity : AppCompatActivity(), HomePresenter.View {

    @Inject
    lateinit var presenter: HomePresenter
    private val TAG: String = "HomeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        init()
    }

    private fun init() {
        (application as App).component.inject(this)
        presenter.bindView(this)
        doRequest(PreferencesManager(this).cookie)
    }

    private fun setupList(classHome: HomeScheduleResponse) {
        val sectionAdapter = SectionedRecyclerViewAdapter()

//        classHome.homeScheduleModel.scheduleDetail.forEach {
//            sectionAdapter.addSection(HomeSection(classHome))
//        }

        sectionAdapter.addSection(HomeSection(classHome))

        rvHome.layoutManager = LinearLayoutManager(baseContext) as RecyclerView.LayoutManager?
        rvHome.adapter = sectionAdapter
    }

    fun doRequest(cookie: CookieDTO) {
//        presenter.getHome(cookie)
        cookie.cookie = "PHPSESSID=i0h77gs2g19rj2m3o462q509s1; path=/"
        presenter.getHome(cookie)
    }

    override fun successRequest(classHome: HomeScheduleResponse) {
        Log.d(TAG, "home: " + classHome.name)
        setupList(classHome)
    }

    override fun badRequest(errorMessage: String) {
        //Apresentar dados na activity
    }

}