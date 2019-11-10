package com.bogdanpoh.customitunes.activities

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.bogdanpoh.customitunes.R
import com.bogdanpoh.customitunes.room.AppDatabase
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoriteActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        val db = AppDatabase(this)

        GlobalScope.launch {
            val audiobooks = db.showDao().getAllAudiobooks()
            val movies = db.showDao().getAllMovies()
            val podcast = db.showDao().getAllPodcasts()

            audiobooks.let {
                it.forEach {
                    test.text =
                        test.text.toString() + ' ' + it.artistName + " - " + it.collectionId.toString() + '\n'
                }
            }

            movies.let {
                it.forEach {
                    test.text =
                        test.text.toString() + ' ' + it.artistName + " - " + it.collectionId.toString() + '\n'
                }
            }

            podcast.let {
                it.forEach {
                    test.text =
                        test.text.toString() + ' ' + it.artistName + " - " + it.collectionId.toString() + '\n'
                }
            }
        }

    }
}
