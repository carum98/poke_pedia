package com.example.pokepedia.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.pokepedia.db.entities.PokemonEntity

@Dao
interface PokemonDAO {
    @Query("SELECT * FROM pokemon_favoritos WHERE userId = :userId")
    fun getAllFavoritePokemon(userId: Int): LiveData< List<PokemonEntity> >

    @Query("Select  * from pokemon_favoritos where idApi=:id Limit 1")
    fun getFavoritePokemon(id:String): LiveData< PokemonEntity >

    @Query("Select  * from pokemon_favoritos where nombre like  :nombreB  Limit 1")
    fun getFavoritePokemonByName(nombreB:String): LiveData< PokemonEntity >

    @Insert
    fun insertPokemonFavorito(PokemonEntity: PokemonEntity)

    @Query("DELETE FROM pokemon_favoritos WHERE idApi = :id")
    fun deletePokemonFavorito(id: String)
}