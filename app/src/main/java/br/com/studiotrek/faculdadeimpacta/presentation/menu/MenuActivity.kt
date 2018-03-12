package br.com.studiotrek.faculdadeimpacta.presentation.menu

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import br.com.studiotrek.faculdadeimpacta.App
import br.com.studiotrek.faculdadeimpacta.R
import br.com.studiotrek.faculdadeimpacta.presentation.base.ViewPagerAdapter
import br.com.studiotrek.faculdadeimpacta.presentation.home.HomeFragment
import br.com.studiotrek.faculdadeimpacta.presentation.schedule.ScheduleFragment
import br.com.studiotrek.faculdadeimpacta.presentation.semesterGrades.SemesterFragment
import br.com.studiotrek.faculdadeimpacta.utils.BottonNavigationViewHelper
import kotlinx.android.synthetic.main.activity_menu.*
import javax.inject.Inject
import br.com.studiotrek.faculdadeimpacta.presentation.MainActivity
import br.com.studiotrek.faculdadeimpacta.utils.Analytics
import br.com.studiotrek.faculdadeimpacta.utils.PreferencesManager
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.google.firebase.analytics.FirebaseAnalytics
import hotchemi.android.rate.OnClickButtonListener
import hotchemi.android.rate.AppRate

/**
 * Created by kleber on 02/03/2018.
 */
class MenuActivity : AppCompatActivity(), MenuPresenter.View {

    private lateinit var mInterstitialAd: InterstitialAd
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    @Inject
    lateinit var presenter: MenuPresenter
    private val TAG: String = "MenuActivity"
    lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        init()
    }

    override fun onResume() {
        super.onResume()
        viewpager.addOnPageChangeListener(mOnPageChangeListener)
    }

    private fun init() {
        (application as App).component.inject(this)
        presenter.bindView(this)

        trackUsageForRateDialog()
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        setupViewPager(viewpager)
        BottonNavigationViewHelper.removeShiftMode(navigation)
        navigation.itemIconTintList = null
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        viewpager.addOnPageChangeListener(mOnPageChangeListener)
        showAdMob()
    }

    private fun showAdMob() {
        MobileAds.initialize(this,
                "ca-app-pub-7595823355776731~5949095136")

        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-7595823355776731/5906176184"
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        mInterstitialAd.adListener = object: AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                if (mInterstitialAd.isLoaded) {
                    mInterstitialAd.show()
                }
            }

            override fun onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
            }
        }
    }

    private fun trackUsageForRateDialog() {
        AppRate.with(this)
                .setInstallDays(3)
                .setLaunchTimes(6)
                .setRemindInterval(3)
                .setShowLaterButton(true)
                .setDebug(false)
                .monitor()
        AppRate.showRateDialogIfMeetsConditions(this)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(HomeFragment.newInstance())
        adapter.addFragment(SemesterFragment.newInstance())
        adapter.addFragment(ScheduleFragment.newInstance())
//        adapter.addFragment(CardAccessFragment.newInstance())

        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = adapter.count


    }

    private val mOnPageChangeListener = (object : ViewPager.OnPageChangeListener {

        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

        }
        override fun onPageSelected(position: Int) {
            navigation.selectedItemId = navigation.menu.getItem(position).itemId
        }

    })

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
//            R.id.navigation_card -> {
//                viewpager.setCurrentItem(3,true)
//                return@OnNavigationItemSelectedListener true
//            }
        }
        false
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.about_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.about -> {
                Analytics.logVisit(firebaseAnalytics, PreferencesManager(this).user?.ra!!, "about_dialog")
                displayAboutDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun displayAboutDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Sobre")
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.about_dialog, null)
        dialogBuilder.setView(dialogView)
                .setPositiveButton("OK", { dialog, whichButton ->
                    dialog.cancel()
                })
        val dialog = dialogBuilder.create()
        dialog.show()
    }
}