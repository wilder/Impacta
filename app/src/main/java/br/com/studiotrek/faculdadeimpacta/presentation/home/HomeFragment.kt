package br.com.studiotrek.faculdadeimpacta.presentation.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.studiotrek.faculdadeimpacta.App
import br.com.studiotrek.faculdadeimpacta.R
import br.com.studiotrek.faculdadeimpacta.domain.entity.CookieDTO
import br.com.studiotrek.faculdadeimpacta.utils.PreferencesManager
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_login.*
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
        return inflater.inflate(R.layout.activity_home, container, false)
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
        val sectionAdapter = SectionedRecyclerViewAdapter()

        sectionAdapter.addSection(HomeSection(classHome))

        rvHome.layoutManager = LinearLayoutManager(activity) as RecyclerView.LayoutManager?
        rvHome.adapter = sectionAdapter
    }

    fun doRequest(cookie: CookieDTO) {
        presenter.getHome(cookie)
        presenter.getHome(cookie)
    }

    override fun successRequest(classHome: HomeScheduleResponse) {
        Log.d(TAG, "home: " + classHome.name)
        pbHome.visibility = View.INVISIBLE
        setupList(classHome)
    }

    override fun badRequest(errorMessage: String) {
        //Apresentar dados na activity
        pbHome.visibility = View.INVISIBLE
    }

}