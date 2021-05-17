package com.example.pokepedia.repositories

import android.content.Context
import com.example.pokepedia.db.DataBase
import com.example.pokepedia.db.emtities.UserEntity

class UserRepository(context: Context) {
    private  val db: DataBase = DataBase.getDatabase(context)

    fun findUser(user: UserEntity) : UserEntity {
        return db.userDAO().findUser(user.name, user.gender)
    }

    fun insertUser(user: UserEntity){
        db.userDAO().insertUser(user)
    }

    fun updateUser(user: UserEntity) {
        db.userDAO().updateUser(user)
    }
}