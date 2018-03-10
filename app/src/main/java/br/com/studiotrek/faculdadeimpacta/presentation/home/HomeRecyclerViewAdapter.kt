package br.com.studiotrek.faculdadeimpacta.presentation.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.studiotrek.faculdadeimpacta.R
import br.com.studiotrek.faculdadeimpacta.presentation.gradesAbsence.GrandesAbsenceAdapter
import br.com.studiotrek.faculdadeimpacta.presentation.schedule.ScheduleDetailModel
import kotlinx.android.synthetic.main.schedule_item.view.*

/**
 * Created by kleber on 02/03/2018.
 */

class HomeRecyclerViewAdapter(var classHome: HomeScheduleResponse) : RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.schedule_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return classHome.homeScheduleModel.scheduleDetail?.size!!
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder as ViewHolder
        val scheduledDetail = classHome.homeScheduleModel.scheduleDetail?.get(position)
        holder.bind(scheduledDetail!!)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(scheduleDetail: ScheduleDetailModel) {
            with (scheduleDetail) {
                itemView.tvClassTitle.text = className
                itemView.tvProfessorName.text = professor
                itemView.tvClassroom.text = room
            }
        }
    }

}