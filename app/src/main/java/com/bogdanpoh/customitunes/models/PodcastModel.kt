package com.bogdanpoh.customitunes.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.bogdanpoh.customitunes.presents.MainPresenter
import com.bogdanpoh.customitunes.room.ListStringConverter

data class PodcastModel(
    val resultCount: Int,
    val results: ArrayList<Podcast>
)

@Entity(tableName = MainPresenter.podcast)
data class Podcast(
    @ColumnInfo(name = "artistId") var artistId: Int,
    @ColumnInfo(name = "artistName") var artistName: String,
    @ColumnInfo(name = "artistViewUrl") var artistViewUrl: String,
    @ColumnInfo(name = "artworkUrl100") var artworkUrl100: String,
    @ColumnInfo(name = "artworkUrl30") var artworkUrl30: String,
    @ColumnInfo(name = "artworkUrl60") var artworkUrl60: String,
    @ColumnInfo(name = "artworkUrl600") var artworkUrl600: String,
    @ColumnInfo(name = "collectionCensoredName") var collectionCensoredName: String,
    @ColumnInfo(name = "collectionExplicitness") var collectionExplicitness: String,
    @ColumnInfo(name = "collectionHdPrice") var collectionHdPrice: Int,
    @PrimaryKey var collectionId: Int,
    @ColumnInfo(name = "collectionName") var collectionName: String,
    @ColumnInfo(name = "collectionPrice") var collectionPrice: Double,
    @ColumnInfo(name = "collectionViewUrl") var collectionViewUrl: String,
    @ColumnInfo(name = "contentAdvisoryRating") var contentAdvisoryRating: String,
    @ColumnInfo(name = "country") var country: String,
    @ColumnInfo(name = "currency") var currency: String,
    @ColumnInfo(name = "feedUrl") var feedUrl: String,

    @TypeConverters(ListStringConverter::class) var genreIds: List<String>,
    @TypeConverters(ListStringConverter::class) var genres: List<String>,

    @ColumnInfo(name = "kind") var kind: String,
    @ColumnInfo(name = "primaryGenreName") var primaryGenreName: String,
    @ColumnInfo(name = "releaseDate") var releaseDate: String,
    @ColumnInfo(name = "trackCensoredName") var trackCensoredName: String,
    @ColumnInfo(name = "trackCount") var trackCount: Int,
    @ColumnInfo(name = "trackExplicitness") var trackExplicitness: String,
    @ColumnInfo(name = "trackHdPrice") var trackHdPrice: Int,
    @ColumnInfo(name = "trackHdRentalPrice") var trackHdRentalPrice: Int,
    @ColumnInfo(name = "trackId") var trackId: Int,
    @ColumnInfo(name = "trackName") var trackName: String,
    @ColumnInfo(name = "trackPrice") var trackPrice: Double,
    @ColumnInfo(name = "trackRentalPrice") var trackRentalPrice: Int,
    @ColumnInfo(name = "trackViewUrl") var trackViewUrl: String,
    @ColumnInfo(name = "wrapperType") var wrapperType: String
)