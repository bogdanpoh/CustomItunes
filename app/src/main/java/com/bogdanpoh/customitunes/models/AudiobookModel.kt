package com.bogdanpoh.customitunes.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bogdanpoh.customitunes.presents.MainPresenter

data class AudiobookModel(
    var resultCount: Int,
    var results: ArrayList<Audiobook>
)

@Entity(tableName = MainPresenter.audiobook)
data class Audiobook(
    @ColumnInfo(name = "wrapperType") var wrapperType: String,
    @ColumnInfo(name = "artistId") var artistId: Int,
    @PrimaryKey var collectionId: Int,
    @ColumnInfo(name = "artistName") var artistName: String,
    @ColumnInfo(name = "collectionName") var collectionName: String,
    @ColumnInfo(name = "collectionCensoredName") var collectionCensoredName: String,
    @ColumnInfo(name = "artistViewUrl") var artistViewUrl: String,
    @ColumnInfo(name = "collectionViewUrl") var collectionViewUrl: String,
    @ColumnInfo(name = "artworkUrl60") var artworkUrl60: String,
    @ColumnInfo(name = "artworkUrl100") var artworkUrl100: String,
    @ColumnInfo(name = "collectionPrice") var collectionPrice: Float,
    @ColumnInfo(name = "collectionExplicitness") var collectionExplicitness: String,
    @ColumnInfo(name = "trackCount") var trackCount: Int,
    @ColumnInfo(name = "country") var country: String,
    @ColumnInfo(name = "currency") var currency: String,
    @ColumnInfo(name = "releaseDate") var releaseDate: String,
    @ColumnInfo(name = "primaryGenreName") var primaryGenreName: String,
    @ColumnInfo(name = "previewUrl") var previewUrl: String,
    @ColumnInfo(name = "description") var description: String
)
