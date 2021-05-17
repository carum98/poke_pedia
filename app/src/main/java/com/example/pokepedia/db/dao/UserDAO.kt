package com.example.pokepedia.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokepedia.db.emtities.UserEntity

@Dao
interface UserDAO {
    @Query("SELECT * FROM user WHERE isActive = 1")
    fun getUser() : UserEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: UserEntity)
}