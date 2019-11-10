package com.bogdanpoh.customitunes.apiServices

import com.bogdanpoh.customitunes.models.AudiobookModel
import com.bogdanpoh.customitunes.models.MainModel
import com.bogdanpoh.customitunes.models.MovieModel
import com.bogdanpoh.customitunes.models.PodcastModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//"/lookup?id={id}"

interface ItunesApiService {

    @GET("/api/v1/us/audiobooks/top-audiobooks/all/25/non-explicit.json")
    fun getAudiobooks(): Call<MainModel>

    @GET("/api/v1/us/movies/top-movies/all/25/non-explicit.json")
    fun getMovies(): Call<MainModel>

    @GET("/api/v1/us/podcasts/top-podcasts/all/25/non-explicit.json")
    fun getPodcasts(): Call<MainModel>

    @GET("/lookup")
    fun getAudiobook(@Query("id") id: Int): Call<AudiobookModel>

    @GET("/lookup")
    fun getMovie(@Query("id") id: Int): Call<MovieModel>

    @GET("/lookup")
    fun getPodcast(@Query("id") id: Int): Call<PodcastModel>
}