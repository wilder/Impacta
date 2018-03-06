package br.com.studiotrek.faculdadeimpacta.presentation.semester_grades

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
import br.com.studiotrek.faculdadeimpacta.utils.PreferencesManager
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_schedule.*
import kotlinx.android.synthetic.main.activity_semester.*
import javax.inject.Inject

/**
 * Created by Kleber on 21/02/2018.
 */
class SemesterFragment : Fragment(), SemesterPresenter.View {

    @Inject
    lateinit var presenter: SemesterPresenter
    private val TAG: String = "SemesterPresenter"

    companion object {
        fun newInstance() = SemesterFragment() as Fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.activity_semester, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        ((activity!!.application) as App).component.inject(this)
        presenter.bindView(this)

        pbSemester.visibility = View.VISIBLE
        doRequest(PreferencesManager(context!!).cookie)
    }

    private fun setupList(semesterResponse : SemesterResponse) {
        val sectionAdapter = SectionedRecyclerViewAdapter()

        sectionAdapter.addSection(SemesterSection(semesterResponse))

        rvSemester.layoutManager = LinearLayoutManager(context!!) as RecyclerView.LayoutManager?
        rvSemester.adapter = sectionAdapter
    }

    fun doRequest(cookie: CookieDTO) {
        presenter.getSemester(cookie)
    }

    override fun successRequest(semesterResponse: SemesterResponse) {
        pbSemester.visibility = View.INVISIBLE
        setupList(semesterResponse)
    }

    override fun badRequest(errorMessage: String) {
        pbSemester.visibility = View.INVISIBLE
    }
}