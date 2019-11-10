package com.bogdanpoh.customitunes.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListStringConverter {

    @TypeConverter
    fun fromGenre(data: String): List<String> {

        val listType = object : TypeToken<ArrayList<String>>() {

        }.type

        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun fromList(data: List<String>): String {
        return Gson().toJson(data)
    }

}