package com.example.pokepedia.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_favoritos")
data class PokemonEntity(
    @ColumnInfo(name = "idApi") val idApi: String,
    @ColumnInfo(name = "nombre") val nombre: String
){
    @PrimaryKey(autoGenerate = true) var identificador: Int=0
}
