package com.bogdanpoh.customitunes.room

import androidx.room.*
import com.bogdanpoh.customitunes.models.Audiobook
import com.bogdanpoh.customitunes.models.Movie
import com.bogdanpoh.customitunes.models.Podcast

@Dao
interface ShowDao {

    @Query("SELECT * FROM audiobook")
    fun getAllAudiobooks(): List<Audiobook>

    @Insert
    fun insertAudiobook(vararg audiobook: Audiobook)

    @Delete
    fun deleteAudiobook(audiobook: Audiobook)

    @Update
    fun updateAudiobooks(vararg audiobook: Audiobook)

    @Query("SELECT * FROM movie")
    fun getAllMovies(): List<Movie>

    @Insert
    fun insertMovie(vararg movie: Movie)

    @Delete
    fun deleteMovie(movie: Movie)

    @Update
    fun updateMovies(vararg movie: Movie)

    @Query("SELECT * FROM podcast")
    fun getAllPodcasts(): List<Podcast>

    @Insert
    fun insertPodcast(vararg podcast: Podcast)

    @Delete
    fun deletePodcast(podcast: Podcast)

    @Update
    fun updatePodcasts(vararg podcast: Podcast)
}