package br.com.studiotrek.faculdadeimpacta.presentation.cardAccess

import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.studiotrek.faculdadeimpacta.R
import br.com.studiotrek.faculdadeimpacta.presentation.schedule.SchedulePresenter
import kotlinx.android.synthetic.main.fragment_card_access.*
import javax.inject.Inject

/**
 * Created by kleber on 09/03/2018.
 */
class CardAccessFragment : Fragment() {

    @Inject
    lateinit var presenter: SchedulePresenter
    private val TAG: String = "CardAccessFragment"

    companion object {
        fun newInstance() = CardAccessFragment() as Fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_card_access, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

    }

}