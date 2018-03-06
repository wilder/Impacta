package br.com.studiotrek.faculdadeimpacta.presentation.semester_grades

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import br.com.studiotrek.faculdadeimpacta.App
import br.com.studiotrek.faculdadeimpacta.R
import br.com.studiotrek.faculdadeimpacta.presentation.grades_absence.GrandesAbsenceFragment
import br.com.studiotrek.faculdadeimpacta.presentation.menu.MenuActivity
import br.com.studiotrek.faculdadeimpacta.presentation.menu.MenuActivity_MembersInjector
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection
import kotlinx.android.synthetic.main.activity_menu.view.*
import kotlinx.android.synthetic.main.semester_item.view.*

/**
 * Created by kleber on 02/03/2018.
 */
class SemesterSection(var classSemester: SemesterResponse) : StatelessSection(R.layout.semester_item) {

    override fun getHeaderViewHolder(view: View): RecyclerView.ViewHolder {
        return HeaderViewHolder(view)
    }

    override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder?) {
        holder as HeaderViewHolder?
//        holder?.bind(classSemester.day)
    }

    class HeaderViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(title: String) {
//            view.tvDayHeader.text = title
        }
    }

    override fun getContentItemsTotal(): Int {
        return classSemester.semesterModel.size;
    }

    override fun getItemViewHolder(view: View): RecyclerView.ViewHolder {
        return ItemViewHolder(view)
    }

    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ItemViewHolder
        val semesterDetailModel = classSemester.semesterModel.get(position)
        holder.bind(semesterDetailModel)
    }

    internal inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(semesterDetailModel: SemesterDetailModel) {
            with (semesterDetailModel) {

                itemView.bnView.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(v: View?) {
                        Toast.makeText(itemView.context, "Houve clique", Toast.LENGTH_SHORT).show()
                    }
                })

                itemView.tvSemester.text = semesterDetailModel.semestre
            }
        }

    }

}