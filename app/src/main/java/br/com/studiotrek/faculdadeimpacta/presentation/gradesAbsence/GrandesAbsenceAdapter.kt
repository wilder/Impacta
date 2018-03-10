package br.com.studiotrek.faculdadeimpacta.presentation.gradesAbsence

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.studiotrek.faculdadeimpacta.R
import kotlinx.android.synthetic.main.grades_absence_item.view.*

/**
 * Created by kleber on 06/03/2018.
 */

class GrandesAbsenceAdapter(var classGradesAbsence: GradesAbsenceResponse) : RecyclerView.Adapter<GrandesAbsenceAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.grades_absence_item, parent, false)
        return GrandesAbsenceAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return classGradesAbsence.grades.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val gradesAbsenceModel = classGradesAbsence.grades.get(position)
        holder?.bind(gradesAbsenceModel)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

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