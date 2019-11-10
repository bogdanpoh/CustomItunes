package com.bogdanpoh.customitunes.models

data class MainModel(
    var feed: Feed
)

data class Feed(
    var title: String,
    var id: String,
    var author: Author,
    var links: ArrayList<Link>,
    var copyright: String,
    var country: String,
    var icon: String,
    var updated: String,
    var results: ArrayList<Answer>
)

data class Answer(
    var artistName: String,
    var id: String,
    var releaseDate: String,
    var name: String,
    var kind: String,
    var artistId: String,
    var artistUrl: String,
    var artworkUrl100: String,
    var genres: ArrayList<Genre>,
    var url: String
)

data class Author(
    var name: String,
    var uri: String
)

data class Link(var self: String)

data class Genre(
    var genreId: String,
    var name: String,
    var url: String
)