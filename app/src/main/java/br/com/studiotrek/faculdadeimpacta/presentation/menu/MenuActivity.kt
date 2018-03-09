package br.com.studiotrek.faculdadeimpacta.presentation.menu

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import br.com.studiotrek.faculdadeimpacta.App
import br.com.studiotrek.faculdadeimpacta.R
import br.com.studiotrek.faculdadeimpacta.presentation.base.ViewPagerAdapter
import br.com.studiotrek.faculdadeimpacta.presentation.cardAccess.CardAccessFragment
import br.com.studiotrek.faculdadeimpacta.presentation.home.HomeFragment
import br.com.studiotrek.faculdadeimpacta.presentation.schedule.ScheduleFragment
import br.com.studiotrek.faculdadeimpacta.presentation.semesterGrades.SemesterFragment
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
        navigation.itemIconTintList = null
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(HomeFragment.newInstance())
        adapter.addFragment(SemesterFragment.newInstance())
        adapter.addFragment(ScheduleFragment.newInstance())
        adapter.addFragment(CardAccessFragment.newInstance())

        viewPager.adapter = adapter
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                viewpager.setCurrentItem(0,true)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_grades -> {
                viewpager.setCurrentItem(1,true)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_schedule -> {
                viewpager.setCurrentItem(2,true)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_card -> {
                viewpager.setCurrentItem(3,true)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

}