package com.example.pokepedia.db.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.pokepedia.db.emtities.UserEntity

@Dao
interface UserDAO {
    @Insert
    fun insertUser(user: UserEntity)
}