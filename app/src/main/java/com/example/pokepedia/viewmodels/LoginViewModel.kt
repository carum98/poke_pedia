package com.example.pokepedia.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.pokepedia.db.emtities.UserEntity
import com.example.pokepedia.repositories.UserRepository

class LoginViewModel(application: Application): AndroidViewModel(application) {
    val repository = UserRepository(application.applicationContext)

    fun findUser(user: UserEntity) : UserEntity {
        return repository.findUser(user);
    }

    fun insertUser(user: UserEntity) {
        repository.insertUser(user);
    }

    fun updateUser(user: UserEntity) {
        repository.updateUser(user)
    }
}