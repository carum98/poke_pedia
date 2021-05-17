package com.example.pokepedia.db.dao

import androidx.room.*
import com.example.pokepedia.db.emtities.UserEntity

@Dao
interface UserDAO {
    @Query("SELECT * FROM user WHERE isActive = 1")
    fun getUser() : UserEntity

    @Query("SELECT * FROM user WHERE name = :name AND gender = :gender")
    fun findUser(name: String, gender: String) : UserEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: UserEntity)

    @Update
    fun updateUser(user: UserEntity)
}