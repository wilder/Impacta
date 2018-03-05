package br.com.studiotrek.faculdadeimpacta.presentation.menu

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import br.com.studiotrek.faculdadeimpacta.App
import br.com.studiotrek.faculdadeimpacta.R
import br.com.studiotrek.faculdadeimpacta.presentation.home.HomeFragment
import br.com.studiotrek.faculdadeimpacta.presentation.home.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_menu.*
import javax.inject.Inject

@SuppressLint("Registered")
/**
 * Created by kleber on 02/03/2018.
 */
class MenuActivity : AppCompatActivity(), MenuPresenter.View {

    @Inject
    lateinit var presenter: MenuPresenter
    private val TAG: String = "MenuActivity"
    lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        init()
    }

    private fun init() {
        (application as App).component.inject(this)
        presenter.bindView(this)
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        setupViewPager(viewpager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment.newInstance())
        viewPager.adapter = adapter
    }

}