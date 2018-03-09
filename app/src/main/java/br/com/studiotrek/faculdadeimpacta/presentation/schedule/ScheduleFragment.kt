package br.com.studiotrek.faculdadeimpacta.presentation.schedule

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import br.com.studiotrek.faculdadeimpacta.App
import br.com.studiotrek.faculdadeimpacta.R
import br.com.studiotrek.faculdadeimpacta.utils.PreferencesManager
import javax.inject.Inject
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.studiotrek.faculdadeimpacta.domain.entity.CookieDTO
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_schedule.*


/**
 * Created by Kleber on 20/02/2018.
 */
class ScheduleFragment : Fragment(), SchedulePresenter.View {

    @Inject
    lateinit var presenter: SchedulePresenter
    private val TAG: String = "ScheduleFragment"

    companion object {
        fun newInstance() = ScheduleFragment() as Fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.activity_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        ((activity!!.application) as App).component.inject(this)
        presenter.bindView(this)

        pbSchedule.visibility = View.VISIBLE
        getSchedule(PreferencesManager(context!!).cookie)
    }

    private fun setupList(classSchedule: List<ScheduleResponse>) {
        val sectionAdapter = SectionedRecyclerViewAdapter()

        classSchedule.forEach {
            sectionAdapter.addSection(ScheduleSection(it))
        }

        rvSchedule.layoutManager = LinearLayoutManager(context!!) as RecyclerView.LayoutManager?
        rvSchedule.adapter = sectionAdapter
    }

    fun getSchedule(cookie : CookieDTO) {
        presenter.getSchedule(cookie)
    }

    override fun successRequest(classSchedule: List<ScheduleResponse>) {
        Log.d(TAG, "Schedule: "+ classSchedule.size)
        pbSchedule.visibility = View.INVISIBLE
        setupList(classSchedule)
    }

    override fun badRequest(errorMessage: String) {
        //Apresentar dados na activity
        pbSchedule.visibility = View.INVISIBLE
    }

}