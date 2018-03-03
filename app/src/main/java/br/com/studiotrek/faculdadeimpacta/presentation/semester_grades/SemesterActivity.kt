package br.com.studiotrek.faculdadeimpacta.presentation.semester_grades

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import br.com.studiotrek.faculdadeimpacta.App
import br.com.studiotrek.faculdadeimpacta.R
import br.com.studiotrek.faculdadeimpacta.domain.entity.CookieDTO
import br.com.studiotrek.faculdadeimpacta.utils.PreferencesManager
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_semester.*
import javax.inject.Inject

/**
 * Created by Kleber on 21/02/2018.
 */
class SemesterActivity : AppCompatActivity(), SemesterPresenter.View {

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
        doRequest(PreferencesManager(this).cookie)
    }

    private fun setupList(semesterResponse : SemesterResponse) {
        val sectionAdapter = SectionedRecyclerViewAdapter()

//        classSemester.forEach {
//            sectionAdapter.addSection(SemesterSection(it))
//        }

        sectionAdapter.addSection(SemesterSection(semesterResponse.semesterModel))

        rvSemester.layoutManager = LinearLayoutManager(baseContext) as RecyclerView.LayoutManager?
        rvSemester.adapter = sectionAdapter
    }

    fun doRequest(cookie: CookieDTO) {
        cookie.cookie = "PHPSESSID=ktotp0l2tgissgkiilo19mmit1; path=/"
        presenter.getSemester(cookie)
    }

    override fun successRequest(semesterResponse: SemesterResponse) {
        setupList(semesterResponse)
    }

    override fun badRequest(errorMessage: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}