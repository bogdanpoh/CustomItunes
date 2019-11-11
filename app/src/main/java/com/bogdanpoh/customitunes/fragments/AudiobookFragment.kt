package com.bogdanpoh.customitunes.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bogdanpoh.customitunes.R
import com.bogdanpoh.customitunes.adapters.RecyclerAdapter
import com.bogdanpoh.customitunes.models.Audiobook
import com.bogdanpoh.customitunes.room.AppDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AudiobookFragment() : Fragment() {

    private lateinit var listAudiobook: List<Audiobook>

    constructor(audiobook: List<Audiobook>) : this() {
        this.listAudiobook = audiobook
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_list, container, false)
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerAdapter = RecyclerAdapter()

        val swipeRefresh = view.findViewById<SwipeRefreshLayout>(R.id.fragment_swipe_refresh)

        swipeRefresh.setOnRefreshListener {

            GlobalScope.launch {
                val db = AppDatabase(view.context)
                val audiobooks = db.showDao().getAllAudiobooks()
                recyclerAdapter.setupList(recyclerAdapter.audiobookToMainModel(listAudiobook = audiobooks))
            }

        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.fragment_recycler_view)

        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(view.context, OrientationHelper.VERTICAL, false)
        recyclerView.setHasFixedSize(true)

        recyclerAdapter.setupList(
            recyclerAdapter.audiobookToMainModel(listAudiobook = listAudiobook)
        )
    }
}