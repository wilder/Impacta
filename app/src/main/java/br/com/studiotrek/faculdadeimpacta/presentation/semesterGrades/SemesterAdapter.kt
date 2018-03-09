package br.com.studiotrek.faculdadeimpacta.presentation.semesterGrades

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.studiotrek.faculdadeimpacta.R
import br.com.studiotrek.faculdadeimpacta.presentation.gradesAbsence.GrandesAbsenceActivity
import kotlinx.android.synthetic.main.semester_item.view.*

/**
 * Created by kleber on 02/03/2018.
 */
class SemesterAdapter(var classSemester: SemesterResponse) : RecyclerView.Adapter<SemesterAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(classSemester.semesterModel[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.semester_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return classSemester.semesterModel.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(semesterDetailModel: SemesterDetailModel) {
            with (semesterDetailModel) {

                itemView.bnView.setOnClickListener {
                    val intent = Intent(itemView.context, GrandesAbsenceActivity::class.java)
                    val bundle = Bundle()
                    bundle.putString("urlSemestre", semesterDetailModel.urlBoletim)
                    intent.putExtras(bundle)
                    itemView.context.startActivity(intent)
                }

                itemView.tvSemester.text = semesterDetailModel.semestre
            }
        }

    }

}