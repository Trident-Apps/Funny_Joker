package com.stanleyhks.b.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UrlEntity::class], version = 1)
abstract class UrlDatabase : RoomDatabase() {

    abstract val urlDao: UrlDao

    companion object {

        private const val dataBaseName = "urldatabase"

        @Volatile
        private var INSTANCE: UrlDatabase? = null
//        private val LOCK = Any()

        fun getInstance(context: Context): UrlDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance =
                        Room.databaseBuilder(context, UrlDatabase::class.java, dataBaseName)
                            .allowMainThreadQueries().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}