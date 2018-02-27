package br.com.studiotrek.faculdadeimpacta.presentation.schedule

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

import br.com.studiotrek.faculdadeimpacta.R
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection

/**
 * Created by Wilder on 26/02/18.
 */

class ScheduleSection(var classSchedule: ScheduleModel) : StatelessSection(R.layout.schedule_header, R.layout.schedule_item) {

    override fun getHeaderViewHolder(view: View): RecyclerView.ViewHolder {
        return HeaderViewHolder(view)
    }

    override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder?) {
        val headerHolder = holder as HeaderViewHolder?
    }

    internal inner class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvDay: TextView

        init {

            tvDay = view.findViewById<View>(R.id.tvDayHeader) as TextView
        }
    }

    internal inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val professor: TextView? = null
        private val className: TextView? = null
        private val room: TextView? = null
    }

    override fun getContentItemsTotal(): Int {
        return classSchedule.datailedSchedule.size
    }

    override fun getItemViewHolder(view: View): RecyclerView.ViewHolder {
        return ItemViewHolder(view)
    }

    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemHolder = holder as ItemViewHolder
    }


}
