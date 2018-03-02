package br.com.studiotrek.faculdadeimpacta.presentation.schedule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import br.com.studiotrek.faculdadeimpacta.App
import br.com.studiotrek.faculdadeimpacta.R
import br.com.studiotrek.faculdadeimpacta.utils.PreferencesManager
import javax.inject.Inject
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import br.com.studiotrek.faculdadeimpacta.domain.entity.CookieDTO
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_schedule.*


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
        doRequest(PreferencesManager(this).cookie)
    }

    private fun setupList(classSchedule: List<ScheduleResponse>) {
        val sectionAdapter = SectionedRecyclerViewAdapter()

        classSchedule.forEach {
            sectionAdapter.addSection(ScheduleSection(it))
        }

        rvSchedule.layoutManager = LinearLayoutManager(baseContext) as RecyclerView.LayoutManager?
        rvSchedule.adapter = sectionAdapter


    }

    fun doRequest(cookie : CookieDTO) {
        presenter.getSchedule(cookie)
    }

    override fun successRequest(classSchedule: List<ScheduleResponse>) {
        Log.d(TAG, "horario: "+ classSchedule.size)
        setupList(classSchedule)
    }

    override fun badRequest(errorMessage: String) {
        //Apresentar dados na activity
    }

}