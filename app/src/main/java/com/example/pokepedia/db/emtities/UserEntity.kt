package com.example.pokepedia.db.emtities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    val name: String,
    val gender: String,
    val isActive: Boolean = false,
) {
    @PrimaryKey(autoGenerate = true) var id: Int=0
}