package com.example.pokepedia.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.pokepedia.db.emtities.UserEntity
import com.example.pokepedia.repositories.UserRepository

class HomeViewModel(application: Application): AndroidViewModel(application) {
    val repository = UserRepository(application.applicationContext)

    fun getUser() : UserEntity {
        return repository.getUser()
    }
}