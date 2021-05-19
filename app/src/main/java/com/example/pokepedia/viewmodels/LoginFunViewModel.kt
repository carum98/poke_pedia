package com.example.pokepedia.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokepedia.db.emtities.UserEntity
import com.example.pokepedia.repositories.UserRepository
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

interface LoginFunViewModelInputs {
    val name: Observer<String>
    val gender: Observer<String>
    val saveClicked: Observer<Unit>
    val isChecked: Observer<Boolean>
}

interface LoginFunViewModelOutputs {
    var isButtonEnabled: Observable<Boolean>
    val nameError: Observable<Boolean>
    val insertNewUser: Observable<Boolean>
}

interface LoginFunViewModelType {
    val inputs: LoginFunViewModelInputs
    val outputs: LoginFunViewModelOutputs
}

class LoginFunViewModel(application: Application) : AndroidViewModel(application),
    LoginFunViewModelInputs,
    LoginFunViewModelOutputs,
    LoginFunViewModelType {
    private var repository: UserRepository

    // TYPE

    override val inputs: LoginFunViewModelInputs = this
    override val outputs: LoginFunViewModelOutputs = this

    // INPUTS
    override val name = BehaviorSubject.create<String>()
    override val gender = BehaviorSubject.create<String>()
    override val saveClicked = PublishSubject.create<Unit>()
    override val isChecked = BehaviorSubject.create<Boolean>()


    // OUTPUTS
    override lateinit var isButtonEnabled: Observable<Boolean>
    override val nameError: Observable<Boolean>
    override val insertNewUser: Observable<Boolean>

    init {
        repository = UserRepository(application.applicationContext)
        isButtonEnabled = Observable.combineLatest(name, isChecked, { n, g -> n.isNotEmpty() && g })
        nameError = name.map { it.isEmpty() }

        insertNewUser = saveClicked
            .withLatestFrom(name, gender, { _, n, g: String -> UserEntity(n, g, true) })
            .doOnNext { acction(it) }
            .map { true }
    }

    private fun acction(user: UserEntity) {
        var userExitente = findUser(user)
        if (userExitente != null) {
            userExitente.isActive = true
            updateUser(userExitente)
        } else {
            insertUser(user)
        }
    }

    fun findUser(user: UserEntity): UserEntity {
        return repository.findUser(user);
    }

    fun insertUser(user: UserEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertUser(user)
        }
    }

    fun updateUser(user: UserEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

}