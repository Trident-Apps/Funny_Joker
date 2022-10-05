package com.stanleyhks.b.repository

import com.stanleyhks.b.model.UrlDao
import com.stanleyhks.b.model.UrlEntity

class JokerRepository(private val dao: UrlDao) {

    val urlEntity = dao.getEntity()

    suspend fun insertUrl(urlEntity: UrlEntity) {
        dao.insertUrl(urlEntity)
    }
}