package br.com.studiotrek.faculdadeimpacta.presentation.menu

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.studiotrek.faculdadeimpacta.App
import br.com.studiotrek.faculdadeimpacta.R
import javax.inject.Inject

/**
 * Created by kleber on 02/03/2018.
 */
class MenuActivity : AppCompatActivity(), MenuPresenter.View {

    @Inject
    lateinit var presenter: MenuPresenter
    private val TAG: String = "MenuActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        init()
    }

    private fun init() {
        (application as App).component.inject(this)
        presenter.bindView(this)
    }

}