package com.stanleyhks.b.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stanleyhks.b.model.UrlDatabase
import com.stanleyhks.b.repository.JokerRepository

class JokerViewModelFactory(
    private val application: Application,
    private val repository: JokerRepository
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return JokerViewModel(repository, application) as T
    }
}