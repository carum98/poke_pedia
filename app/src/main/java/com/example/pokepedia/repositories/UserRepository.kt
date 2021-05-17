package com.example.pokepedia.repositories

import android.content.Context
import com.example.pokepedia.db.DataBase
import com.example.pokepedia.db.emtities.UserEntity

class UserRepository(context: Context) {
    private  val db: DataBase = DataBase.getDatabase(context)

    fun insertUser(user: UserEntity){
        db.userDAO().insertUser(user)
    }
}