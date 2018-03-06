package br.com.studiotrek.faculdadeimpacta.presentation.grades_absence

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
        return GrandesAbsenceSection.HeaderViewHolder(view)
    }

    override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder?) {
        holder as GrandesAbsenceSection.HeaderViewHolder?
//        holder?.bind(classGradesAbsence.grades)
    }

    class HeaderViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(title: String) {
//            view.tvTitleMatters.text = title
        }
    }

    override fun getContentItemsTotal(): Int {
        return classGradesAbsence.grades.size
    }

    override fun getItemViewHolder(view: View): RecyclerView.ViewHolder {
        return ItemViewHolder(view)
    }

    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as GrandesAbsenceSection.ItemViewHolder
        val gradesAbsenceModel = classGradesAbsence.grades.get(position)
        holder.bind(gradesAbsenceModel)
    }

    internal inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(gradesAbsenceModel: GradesAbsenceModel) {
            with (gradesAbsenceModel) {
                itemView.tvTitleMatters.text = gradesAbsenceModel.nomeMateria
                itemView.tvGradeMb1.text = gradesAbsenceModel.json.mb1
                itemView.tvFailureMb1.text = gradesAbsenceModel.json.faltas1
                itemView.tvGradeMb2.text = gradesAbsenceModel.json.mb2
                itemView.tvFailureMb2.text = gradesAbsenceModel.json.faltas2
            }
        }
    }

}