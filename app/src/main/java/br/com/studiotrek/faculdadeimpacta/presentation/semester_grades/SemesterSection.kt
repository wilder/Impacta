package br.com.studiotrek.faculdadeimpacta.presentation.semester_grades

import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.studiotrek.faculdadeimpacta.R
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection
import kotlinx.android.synthetic.main.semester_item.view.*

/**
 * Created by kleber on 02/03/2018.
 */
class SemesterSection(var classSemesterModel: SemesterModel) : StatelessSection(R.layout.semester_item) {

    override fun getHeaderViewHolder(view: View): RecyclerView.ViewHolder {
        return super.getHeaderViewHolder(view)
    }

//    override fun getHeaderViewHolder(view: View): RecyclerView.ViewHolder {
//        return HeaderViewHolder(view)
//    }

    override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder?) {
//        holder as HeaderViewHolder?
//        holder?.bind(classSemester.day)
    }

//    class HeaderViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
//        fun bind(title: String) {
//            view.tvDayHeader.text = title
//        }
//    }

    override fun getContentItemsTotal(): Int {
        return classSemesterModel.semestres.size;
    }

    override fun getItemViewHolder(view: View): RecyclerView.ViewHolder {
        return ItemViewHolder(view)
    }

    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ItemViewHolder
        val semesterDetailModel = classSemesterModel.semestres.get(position)
        holder.bind(semesterDetailModel)
    }

    internal inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(semesterDetailModel: SemesterDetailModel) {
            with (semesterDetailModel) {
                itemView.tvSemester.text = semesterDetailModel.semestre;
//                itemView.tvClassTitle.text = className
//                itemView.tvProfessorName.text = professor
//                itemView.tvClassroom.text = room.trim()
            }
        }
    }

}