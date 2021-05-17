package com.example.pokepedia.db.emtities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_recents")
data class PokemonRecentsEntity(
    @ColumnInfo(name = "idApi") val idApi: String,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "timeStamp") var timeStamp: Long
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}