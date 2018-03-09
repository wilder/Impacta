package br.com.studiotrek.faculdadeimpacta.presentation.gradesAbsence

import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.studiotrek.faculdadeimpacta.R
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection
import kotlinx.android.synthetic.main.grades_absence_item.view.*

/**
 * Created by kleber on 06/03/2018.
 */

class GrandesAbsenceSection(var classGradesAbsence: GradesAbsenceResponse) : StatelessSection(R.layout.grades_absence_item) {

    override fun getHeaderViewHolder(view: View): RecyclerView.ViewHolder {
        return HeaderViewHolder(view)
    }

    override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder?) {
        holder as HeaderViewHolder?
//        holder?.bind(classGradesAbsence.day)
    }

    class HeaderViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(title: String) {
//            view.tvDayHeader.text = title
        }
    }

    override fun getContentItemsTotal(): Int {
        return classGradesAbsence.grades.size
    }

    override fun getItemViewHolder(view: View): RecyclerView.ViewHolder {
        return ItemViewHolder(view)
    }

    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ItemViewHolder
        val scheduledDetail = classGradesAbsence.grades.get(position)
        holder.bind(scheduledDetail)
    }

    internal inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(gradesAbsenceModel: GradesAbsenceModel) {
            with (gradesAbsenceModel) {

                itemView.tvTitleMatters.text = gradesAbsenceModel.nomeMateria

                if(gradesAbsenceModel.json.mb1 == "") {
                    gradesAbsenceModel.json.mb1 = "0"
                } else {
                    val mb1 = gradesAbsenceModel.json.mb1.toDouble()
                    gradesAbsenceModel.json.mb1 = mb1.toString()
                }

                if(gradesAbsenceModel.json.faltas1 == "") {
                    gradesAbsenceModel.json.faltas1 = "0"
                } else {
                    val faltas1 = gradesAbsenceModel.json.faltas1.toDouble().toInt()
                    gradesAbsenceModel.json.faltas1 = faltas1.toString()
                }

                if(gradesAbsenceModel.json.mb2 == "") {
                    gradesAbsenceModel.json.mb2 = "0"
                } else {
                    val mb2 = gradesAbsenceModel.json.mb2.toDouble()
                    gradesAbsenceModel.json.mb2 = mb2.toString()
                }

                if(gradesAbsenceModel.json.faltas2 == "") {
                    gradesAbsenceModel.json.faltas2 = "0"
                } else {
                    val faltas2 = gradesAbsenceModel.json.faltas2.toDouble().toInt()
                    gradesAbsenceModel.json.faltas2 = faltas2.toString()
                }

                itemView.tvGradeMb1.text = gradesAbsenceModel.json.mb1
                itemView.tvFailureMb1.text = gradesAbsenceModel.json.faltas1
                itemView.tvGradeMb2.text = gradesAbsenceModel.json.mb2
                itemView.tvFailureMb2.text = gradesAbsenceModel.json.faltas2
            }
        }
    }

}