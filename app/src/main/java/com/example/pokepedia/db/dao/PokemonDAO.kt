package com.example.pokepedia.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.pokepedia.db.emtities.PokemonRecentsEntity
import com.example.pokepedia.db.entities.PokemonEntity

@Dao
interface PokemonDAO {
    @Query("SELECT * FROM pokemon_favoritos WHERE userId = :userId")
    fun getAllFavoritePokemon(userId: Int): LiveData< List<PokemonEntity> >

    @Query("Select * from pokemon_recents order by timeStamp desc")
    fun getAllRecentsPokemon(): LiveData< List<PokemonRecentsEntity> >

    @Query("Select  * from pokemon_favoritos where idApi=:id Limit 1")
    fun getFavoritePokemon(id:String): LiveData< PokemonEntity >

    @Query("Select  * from pokemon_recents where idApi=:id Limit 1")
    fun getRecentPokemonById(id:String): LiveData< PokemonRecentsEntity >

    @Update
    fun updateRecents(pokemonRecents: PokemonRecentsEntity)

    @Query("Select  * from pokemon_favoritos where nombre like  :nombreB  Limit 1")
    fun getFavoritePokemonByName(nombreB:String): LiveData< PokemonEntity >

    @Insert
    fun insertPokemonFavorito(PokemonEntity: PokemonEntity)

    @Insert
    fun insertRecentPokemon(pokemonRecents: PokemonRecentsEntity)

    @Query("DELETE FROM pokemon_favoritos WHERE idApi = :id")
    fun deletePokemonFavorito(id: String)
}