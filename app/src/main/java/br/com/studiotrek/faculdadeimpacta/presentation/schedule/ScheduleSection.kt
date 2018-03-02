package br.com.studiotrek.faculdadeimpacta.presentation.schedule

import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.studiotrek.faculdadeimpacta.R
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection
import kotlinx.android.synthetic.main.schedule_header.view.*
import kotlinx.android.synthetic.main.schedule_item.view.*

/**
 * Created by Wilder on 26/02/18.
 */

class ScheduleSection(var classSchedule: ScheduleResponse) : StatelessSection(R.layout.schedule_header, R.layout.schedule_item) {

    override fun getHeaderViewHolder(view: View): RecyclerView.ViewHolder {
        return HeaderViewHolder(view)
    }

    override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder?) {
        holder as HeaderViewHolder?
        holder?.bind(classSchedule.day)
    }

    class HeaderViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(title: String) {
            view.tvDayHeader.text = title
        }

    }

    override fun getContentItemsTotal(): Int {
        return classSchedule.datailedSchedule.size
    }

    override fun getItemViewHolder(view: View): RecyclerView.ViewHolder {
        return ItemViewHolder(view)
    }

    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ItemViewHolder
        val scheduledDetail = classSchedule.datailedSchedule.get(position)
        holder.bind(scheduledDetail)
    }

    internal inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(scheduleDetail: ScheduleDetailModel) {
            with (scheduleDetail) {
                itemView.tvClassTitle.text = className
                itemView.tvProfessorName.text = professor
                itemView.tvClassroom.text = room.trim()
            }
        }
    }


}
