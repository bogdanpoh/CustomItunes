package com.bogdanpoh.customitunes.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bogdanpoh.customitunes.presents.MainPresenter

data class MovieModel(
    val resultCount: Int,
    val results: ArrayList<Movie>
)
@Entity(tableName = MainPresenter.movie)
data class Movie(
    @ColumnInfo(name = "artistName") val artistName: String,
    @ColumnInfo(name = "artworkUrl100") val artworkUrl100: String,
    @ColumnInfo(name = "artworkUrl30") val artworkUrl30: String,
    @ColumnInfo(name = "artworkUrl60") val artworkUrl60: String,
    @ColumnInfo(name = "collectionArtistId") val collectionArtistId: Int,
    @ColumnInfo(name = "collectionArtistViewUrl") val collectionArtistViewUrl: String,
    @ColumnInfo(name = "collectionCensoredName") val collectionCensoredName: String,
    @ColumnInfo(name = "collectionExplicitness") val collectionExplicitness: String,
    @ColumnInfo(name = "collectionHdPrice") val collectionHdPrice: Double,
    @PrimaryKey val collectionId: Int,
    @ColumnInfo(name = "collectionName") val collectionName: String,
    @ColumnInfo(name = "collectionPrice") val collectionPrice: Double,
    @ColumnInfo(name = "collectionViewUrl") val collectionViewUrl: String,
    @ColumnInfo(name = "contentAdvisoryRating") val contentAdvisoryRating: String,
    @ColumnInfo(name = "country") val country: String,
    @ColumnInfo(name = "currency") val currency: String,
    @ColumnInfo(name = "discCount") val discCount: Int,
    @ColumnInfo(name = "discNumber") val discNumber: Int,
    @ColumnInfo(name = "hasITunesExtras") val hasITunesExtras: Boolean,
    @ColumnInfo(name = "kind") val kind: String,
    @ColumnInfo(name = "longDescription") val longDescription: String,
    @ColumnInfo(name = "previewUrl") val previewUrl: String,
    @ColumnInfo(name = "primaryGenreName") val primaryGenreName: String,
    @ColumnInfo(name = "releaseDate") val releaseDate: String,
    @ColumnInfo(name = "shortDescription") val shortDescription: String,
    @ColumnInfo(name = "trackCensoredName") val trackCensoredName: String,
    @ColumnInfo(name = "trackCount") val trackCount: Int,
    @ColumnInfo(name = "trackExplicitness") val trackExplicitness: String,
    @ColumnInfo(name = "trackHdPrice") val trackHdPrice: Double,
    @ColumnInfo(name = "trackHdRentalPrice") val trackHdRentalPrice: Double,
    @ColumnInfo(name = "trackId") val trackId: Int,
    @ColumnInfo(name = "trackName") val trackName: String,
    @ColumnInfo(name = "trackNumber") val trackNumber: Int,
    @ColumnInfo(name = "trackPrice") val trackPrice: Double,
    @ColumnInfo(name = "trackRentalPrice") val trackRentalPrice: Double,
    @ColumnInfo(name = "trackTimeMillis") val trackTimeMillis: Int,
    @ColumnInfo(name = "trackViewUrl") val trackViewUrl: String,
    @ColumnInfo(name = "wrapperType") val wrapperType: String
)