package com.bogdanpoh.customitunes.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bogdanpoh.customitunes.models.Audiobook
import com.bogdanpoh.customitunes.models.Movie
import com.bogdanpoh.customitunes.models.Podcast

@Database(entities = [Audiobook::class, Movie::class, Podcast::class], version = 1)
@TypeConverters(ListStringConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun showDao(): ShowDao

    companion object {
        val dbName: String = "custom-itunes.db"

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, dbName).build()
    }
}