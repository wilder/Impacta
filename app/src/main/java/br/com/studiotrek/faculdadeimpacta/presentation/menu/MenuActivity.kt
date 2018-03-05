package br.com.studiotrek.faculdadeimpacta.presentation.menu

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import br.com.studiotrek.faculdadeimpacta.App
import br.com.studiotrek.faculdadeimpacta.R
import br.com.studiotrek.faculdadeimpacta.presentation.home.HomeFragment
import br.com.studiotrek.faculdadeimpacta.presentation.base.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_menu.*
import javax.inject.Inject
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import br.com.studiotrek.faculdadeimpacta.presentation.schedule.ScheduleFragment
import br.com.studiotrek.faculdadeimpacta.presentation.semester_grades.SemesterFragment


@SuppressLint("Registered")
/**
 * Created by kleber on 02/03/2018.
 */
class MenuActivity : AppCompatActivity(), MenuPresenter.View {

    @Inject
    lateinit var presenter: MenuPresenter
    private val TAG: String = "MenuActivity"
    lateinit var viewPagerAdapter: ViewPagerAdapter

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                setupFragmentTransaction(HomeFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_grades -> {
                setupFragmentTransaction(SemesterFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_schedule -> {
                setupFragmentTransaction(ScheduleFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        init()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun init() {
        (application as App).component.inject(this)
        presenter.bindView(this)
//        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
//        setupViewPager(viewpager)

        setupFragmentTransaction(HomeFragment.newInstance())
    }

    private fun setupFragmentTransaction(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.disallowAddToBackStack()
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        fragmentTransaction.commit()
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(HomeFragment.newInstance())
        adapter.addFragment(ScheduleFragment.newInstance())
        adapter.addFragment(SemesterFragment.newInstance())

        viewPager.adapter = adapter
    }
}