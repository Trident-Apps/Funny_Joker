package com.stanleyhks.b.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UrlEntity(
    @PrimaryKey(autoGenerate = true) var uId: Int = 0,
    var url: String? = null,
    var adbStatus: String? = null,
    var appsStatus: Boolean
)
