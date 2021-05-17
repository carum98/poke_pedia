package com.example.pokepedia.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.pokepedia.db.emtities.UserEntity

@Entity(tableName = "pokemon_favoritos", foreignKeys = [
    ForeignKey(
        entity = UserEntity::class,
        parentColumns = ["id"],
        childColumns = ["userId"]
    )
])
data class PokemonEntity(
    val idApi: String,
    val nombre: String,
    var userId: Int = 0,
){
    @PrimaryKey(autoGenerate = true) var identificador: Int=0
}
