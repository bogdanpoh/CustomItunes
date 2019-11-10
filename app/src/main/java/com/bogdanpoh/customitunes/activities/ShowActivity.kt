package com.bogdanpoh.customitunes.activities


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bogdanpoh.customitunes.R
import com.bogdanpoh.customitunes.helpers.ViewHelpers
import com.bogdanpoh.customitunes.models.*
import com.bogdanpoh.customitunes.presents.MainPresenter
import com.bogdanpoh.customitunes.presents.ShowPresenter
import com.bogdanpoh.customitunes.room.AppDatabase
import com.bogdanpoh.customitunes.views.ShowView
import com.r0adkll.slidr.Slidr
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_show.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import maes.tech.intentanim.CustomIntent.customType


class ShowActivity : MvpAppCompatActivity(), ShowView {


    @InjectPresenter
    lateinit var showPresenter: ShowPresenter

    private val TAG = "ShowActivity"

    private lateinit var db: AppDatabase

    private lateinit var toolbarBack: ImageView
    private lateinit var toolbarSave: ImageView
    private lateinit var itemId: String
    private lateinit var itemArtWorkUrl: String
    private lateinit var itemType: String

    private lateinit var mTxtArtistName: TextView
    private lateinit var mTxtCollectionName: TextView
    private lateinit var mImgArtWork: ImageView
    private lateinit var mTxtPrice: TextView
    private lateinit var mTxtReleaseDate: TextView
    private lateinit var mTxtGenre: TextView
    private lateinit var mTxtDescription: TextView

    private lateinit var currentAudiobook: Audiobook
    private lateinit var currentMovie: Movie
    private lateinit var currentPodcast: Podcast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        db = AppDatabase(this)

        toolbarBack = findViewById(R.id.toolbar_back)
        toolbarSave = findViewById(R.id.toolbar_save)

        viewControls()

        toolbarSave.setOnClickListener {
            toolbarSave.setImageResource(R.drawable.ic_save_check)

            GlobalScope.launch {

                when (itemType) {
                    MainPresenter.book -> db.showDao().insertAudiobook(currentAudiobook)
                    MainPresenter.audiobook -> db.showDao().insertAudiobook(currentAudiobook)
                    MainPresenter.movie -> db.showDao().insertMovie(currentMovie)
                    MainPresenter.podcast -> db.showDao().insertPodcast(currentPodcast)
                }

            }
        }

        mTxtArtistName = findViewById(R.id.show_txt_artist_name)
        mTxtCollectionName = findViewById(R.id.show_txt_collection_name)
        mImgArtWork = findViewById(R.id.show_img_art_work)
        mTxtPrice = findViewById(R.id.show_txt_collection_price)
        mTxtReleaseDate = findViewById(R.id.show_txt_release_date)
        mTxtGenre = findViewById(R.id.show_txt_genre)
        mTxtDescription = findViewById(R.id.show_txt_description)

        itemId = intent.getStringExtra("id")
        itemArtWorkUrl = intent.getStringExtra("artWorkUrl")
        itemType = intent.getStringExtra("type")

        val toolbarTitle = findViewById<TextView>(R.id.toolbar_title)
        ViewHelpers.Font.setFontTextViews(R.font.lato, this, arrayListOf(toolbarTitle))

        ViewHelpers.Font.setFontTextViews(
            R.font.lato_regular, this, arrayListOf(
                mTxtArtistName,
                mTxtCollectionName,
                mTxtPrice,
                mTxtReleaseDate,
                mTxtGenre,
                mTxtDescription,
                show_txt_genre_label, show_txt_artist_label, show_txt_price_label
            )
        )

        toolbarBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("type", itemType)
            startActivity(intent)
            customType(this, "right-to-left")
        }

        showPresenter.loadShow(itemId.toInt(), type = itemType)

        Slidr.attach(this)
    }

    override fun bindAudiobook(audiobookModel: AudiobookModel) {

        currentAudiobook = audiobookModel.results[0]

        GlobalScope.launch {

            val list = db.showDao().getAllAudiobooks()

            list.forEach {
                if (it == currentAudiobook) {
                    toolbarSave.setImageResource(R.drawable.ic_save_check)
                }
            }
        }

        currentAudiobook.artistName.let {
            mTxtArtistName.text = it
        }
        currentAudiobook.collectionName.let {
            mTxtCollectionName.text = it
        }

        Picasso.get().load(itemArtWorkUrl).into(mImgArtWork)

        mTxtPrice.text =
            currentAudiobook.collectionPrice.toString() + ' ' + currentAudiobook.currency

        showPresenter.convertDate(currentAudiobook.releaseDate).let {
            mTxtReleaseDate.text = it
        }

        currentAudiobook.primaryGenreName.let {
            mTxtGenre.text = it
        }

        showPresenter.convertHtmlToString(currentAudiobook.description).let {
            mTxtDescription.text = it
        }
    }

    override fun bindMovie(movieModel: MovieModel) {
        currentMovie = movieModel.results[0]

        GlobalScope.launch {

            val list = db.showDao().getAllMovies()

            list.forEach {
                if (it == currentMovie) {
                    toolbarSave.setImageResource(R.drawable.ic_save_check)
                }
            }
        }

        currentMovie.artistName.let {
            mTxtArtistName.text = it
        }
        currentMovie.collectionName.let {
            mTxtCollectionName.text = it
        }

        Picasso.get().load(itemArtWorkUrl).into(mImgArtWork)

        mTxtPrice.text =
            currentMovie.collectionPrice.toString() + ' ' + currentMovie.currency

        showPresenter.convertDate(currentMovie.releaseDate).let {
            mTxtReleaseDate.text = it
        }

        currentMovie.primaryGenreName.let {
            mTxtGenre.text = it
        }

        showPresenter.convertHtmlToString(currentMovie.longDescription).let {
            mTxtDescription.text = it
        }
    }

    override fun bindPodcast(podcastModel: PodcastModel) {
        currentPodcast = podcastModel.results[0]

        GlobalScope.launch {

            val list = db.showDao().getAllPodcasts()

            list.forEach {
                if (it == currentPodcast) {
                    toolbarSave.setImageResource(R.drawable.ic_save_check)
                }
            }
        }

        currentPodcast.artistName.let {
            mTxtArtistName.text = it
        }
        currentPodcast.collectionName.let {
            mTxtCollectionName.text = it
        }

        Picasso.get().load(itemArtWorkUrl).into(mImgArtWork)

        if (currentPodcast.collectionPrice.toString() == "0.0") {
            mTxtPrice.text = getString(R.string.free_price)
        }

        showPresenter.convertDate(currentPodcast.releaseDate).let {
            mTxtReleaseDate.text = it
        }

        currentPodcast.primaryGenreName.let {
            mTxtGenre.text = it
        }

        mTxtDescription.text = " "
    }

    override fun viewControls() {
        toolbarBack.visibility = View.VISIBLE
        toolbarSave.visibility = View.VISIBLE
    }

    override fun errorLoad(message: String) {
        mTxtCollectionName.text = message
    }
}
