package com.example.pokepedia.db.emtities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    val name: String,
    val gender: String,
) {
    @PrimaryKey(autoGenerate = true) var id: Int=0
}