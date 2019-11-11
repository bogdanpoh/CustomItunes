package com.bogdanpoh.customitunes.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bogdanpoh.customitunes.R
import com.bogdanpoh.customitunes.adapters.PageAdapter
import com.bogdanpoh.customitunes.adapters.RecyclerAdapter
import com.bogdanpoh.customitunes.fragments.AudiobookFragment
import com.bogdanpoh.customitunes.fragments.MovieFragment
import com.bogdanpoh.customitunes.fragments.PodcastFragment
import com.bogdanpoh.customitunes.helpers.ViewHelpers
import com.bogdanpoh.customitunes.models.MainModel
import com.bogdanpoh.customitunes.presents.MainPresenter
import com.bogdanpoh.customitunes.room.AppDatabase
import com.bogdanpoh.customitunes.views.MainView
import com.github.rahatarmanahmed.cpv.CircularProgressView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import maes.tech.intentanim.CustomIntent.customType

class MainActivity : MvpAppCompatActivity(), MainView {


    private val TAG = "MainActivity"

    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    private lateinit var mTabs: TabLayout
    private lateinit var mViewPager: ViewPager
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mTxtNoItems: TextView
    private lateinit var mBottomNavigation: BottomNavigationView
    private var currentSelectBottomButton: Int = 0
    private lateinit var mCpv: CircularProgressView
    private lateinit var mRecyclerAdapter: RecyclerAdapter

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbarTitle: TextView = findViewById(R.id.toolbar_title)
        ViewHelpers.Font.setFontTextViews(R.font.lato, this, arrayListOf(toolbarTitle))

        mCpv = findViewById(R.id.main_cpv)
        mTxtNoItems = findViewById(R.id.main_no_items)
        mRecyclerView = findViewById(R.id.main_recycler_view)

        mTabs = findViewById(R.id.main_tabs_view)
        mViewPager = findViewById(R.id.main_view_pager)

        currentSelectBottomButton = R.id.menu_audiobook
        mBottomNavigation = findViewById(R.id.main_bottom_navigation)
        mBottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_audiobook -> {
                    currentSelectBottomButton = R.id.menu_audiobook
                    mainPresenter.loadData(MainPresenter.audiobook)
                    true
                }
                R.id.menu_movie -> {
                    currentSelectBottomButton = R.id.menu_movie
                    mainPresenter.loadData(MainPresenter.movie)
                    true
                }
                R.id.menu_podcast -> {
                    currentSelectBottomButton = R.id.menu_podcast
                    mainPresenter.loadData(MainPresenter.podcast)
                    true
                }
                R.id.menu_favorite -> {
                    showFavorite()
                    true
                }
                else -> false
            }
        }

        val type = intent.getStringExtra("type")

        toolbar_info.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
            customType(this, "left-to-right")
        }

        type.let {
            when (type) {
                MainPresenter.book -> setSelectedBottomButton(R.id.menu_audiobook)
                MainPresenter.audiobook -> setSelectedBottomButton(R.id.menu_audiobook)
                MainPresenter.movie -> setSelectedBottomButton(R.id.menu_movie)
                MainPresenter.podcast -> setSelectedBottomButton(R.id.menu_podcast)
            }
        }

        mRecyclerAdapter = RecyclerAdapter()
        mRecyclerView.adapter = mRecyclerAdapter
        mRecyclerView.layoutManager =
            LinearLayoutManager(
                applicationContext,
                OrientationHelper.VERTICAL,
                false
            ) // OrientationHelper.VERTICAL - WrongConstant
        mRecyclerView.setHasFixedSize(true)

        setupTabLayout()

        mainPresenter.loadData(MainPresenter.Types.audiobook)
    }

    override fun startLoading() {
        mRecyclerView.visibility = View.GONE
        mTxtNoItems.visibility = View.GONE
        mCpv.visibility = View.VISIBLE
    }

    override fun endLoading() {
        mCpv.visibility = View.GONE
    }

    override fun showError(message: Int) {
        mTxtNoItems.text = getString(message)
    }

    override fun setupEmptyList() {
        mCpv.visibility = View.GONE
        mTxtNoItems.visibility = View.VISIBLE
    }

    override fun setupList(mainModel: MainModel) {
        mRecyclerView.visibility = View.VISIBLE
        mTxtNoItems.visibility = View.GONE

        mRecyclerAdapter.setupList(mainModel = mainModel)
    }

    override fun showFavorite() {
        mBottomNavigation.visibility = View.GONE
        mRecyclerView.visibility = View.GONE
        mTabs.visibility = View.VISIBLE
        mViewPager.visibility = View.VISIBLE
        toolbar_hr.visibility = View.GONE
        toolbar_back.visibility = View.VISIBLE
        toolbar_back.setOnClickListener {
            hideFovorite()
        }
    }

    override fun hideFovorite() {
        toolbar_hr.visibility = View.VISIBLE
        toolbar_back.visibility = View.GONE
        mBottomNavigation.visibility = View.VISIBLE
        mRecyclerView.visibility = View.VISIBLE
        mTabs.visibility = View.GONE
        mViewPager.visibility = View.GONE

        mBottomNavigation.selectedItemId = currentSelectBottomButton
    }

    override fun setupTabLayout() {

        val db = AppDatabase(this)

        GlobalScope.launch {

            val adapterPager = PageAdapter(fragmentManager = supportFragmentManager)

            val listAudiobooks = db.showDao().getAllAudiobooks()
            val listMovies = db.showDao().getAllMovies()
            val listPodcasts = db.showDao().getAllPodcasts()

            adapterPager.addFragment(
                AudiobookFragment(listAudiobooks),
                getString(R.string.audiobooks)
            )
            adapterPager.addFragment(MovieFragment(listMovies), getString(R.string.movies))
            adapterPager.addFragment(PodcastFragment(listPodcasts), getString(R.string.podcasts))

            mViewPager.adapter = adapterPager

            mTabs.setupWithViewPager(mViewPager)
        }
    }

    override fun setSelectedBottomButton(id: Int) {
        mBottomNavigation.selectedItemId = id
    }
}
