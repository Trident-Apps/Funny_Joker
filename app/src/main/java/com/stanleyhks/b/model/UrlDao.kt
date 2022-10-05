package com.stanleyhks.b.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UrlDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUrl(urlEntity: UrlEntity)

    @Query("SELECT * FROM urlentity LIMIT 1")
    fun getEntity(): UrlEntity?
}