package com.bogdanpoh.customitunes.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bogdanpoh.customitunes.R
import com.bogdanpoh.customitunes.adapters.AnswersAdapter
import com.bogdanpoh.customitunes.helpers.ViewHelpers
import com.bogdanpoh.customitunes.models.MainModel
import com.bogdanpoh.customitunes.presents.MainPresenter
import com.bogdanpoh.customitunes.views.MainView
import com.github.rahatarmanahmed.cpv.CircularProgressView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : MvpAppCompatActivity(), MainView {

    private val TAG = "MainActivity"

    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mTxtNoItems: TextView
    private lateinit var mBottomNavigation: BottomNavigationView
    private lateinit var mCpv: CircularProgressView
    private lateinit var mAdapter: AnswersAdapter

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbarTitle: TextView = findViewById(R.id.toolbar_title)
        ViewHelpers.Font.setFontTextViews(R.font.lato, this, arrayListOf(toolbarTitle))

        mCpv = findViewById(R.id.main_cpv)
        mTxtNoItems = findViewById(R.id.main_no_items)
        mRecyclerView = findViewById(R.id.main_recycler_view)

        mBottomNavigation = findViewById(R.id.main_bottom_navigation)
        mBottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_audiobook -> {
                    mainPresenter.loadData(MainPresenter.audiobook)
                    true
                }
                R.id.menu_movie -> {
                    mainPresenter.loadData(MainPresenter.movie)
                    true
                }
                R.id.menu_podcast -> {
                    MainPresenter.podcast
                    mainPresenter.loadData(MainPresenter.podcast)
                    true
                }
                R.id.menu_favorite -> {
                    val intent = Intent(this, FavoriteActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        mAdapter = AnswersAdapter()
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager =
            LinearLayoutManager(
                applicationContext,
                OrientationHelper.VERTICAL,
                false
            ) // OrientationHelper.VERTICAL - WrongConstant
        mRecyclerView.setHasFixedSize(true)

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

    override fun setupAnswersList(mainModel: MainModel) {
        mRecyclerView.visibility = View.VISIBLE
        mTxtNoItems.visibility = View.GONE

        mAdapter.setupAnswer(mainModel = mainModel)
    }
}
