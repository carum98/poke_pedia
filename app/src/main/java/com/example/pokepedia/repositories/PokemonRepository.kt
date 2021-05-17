package com.example.pokepedia.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.pokepedia.db.DataBase
import androidx.room.RoomDatabase
import com.example.pokepedia.db.emtities.PokemonRecentsEntity
import com.example.pokepedia.db.entities.PokemonEntity

class PokemonRepository(context: Context) {
    private  val db:DataBase = DataBase.getDatabase(context)

    fun insertPokemonFavorito(pokemon: PokemonEntity){
        val user = db.userDAO().getUser()
        pokemon.userId = user.id
        db.pokemonDAO().insertPokemonFavorito(pokemon)
    }
    fun insertPokemonRecent(Pokemon: PokemonRecentsEntity){
        val user = db.userDAO().getUser()
        Pokemon.userId=user.id
        db.pokemonDAO().insertRecentPokemon(Pokemon)
    }
    fun updatePokemonRecent(Pokemon: PokemonRecentsEntity){
        db.pokemonDAO().updateRecents(Pokemon)
    }
    fun deleteFavoritePokemon(idApi: String){
        db.pokemonDAO().deletePokemonFavorito(idApi)
    }
    fun getAllRecentsPokemon():LiveData<List<PokemonRecentsEntity>> {
        val user = db.userDAO().getUser()
        return db.pokemonDAO().getAllRecentsPokemon(user.id)
    }
    fun getAllFavoritePokemon():LiveData<List<PokemonEntity>> {
        val user = db.userDAO().getUser()
        return db.pokemonDAO().getAllFavoritePokemon(userId = user.id)
    }
    fun getFavoritePokemonById(idApi:String):LiveData<PokemonEntity>  = db.pokemonDAO().getFavoritePokemon(idApi)
    fun getFavoritePokemonByName(name:String):LiveData<PokemonEntity> {
        val user = db.userDAO().getUser()
        return db.pokemonDAO().getFavoritePokemonByName(name,user.id)
    }
    fun getRecentPokemonById(idApi:String):LiveData<PokemonRecentsEntity>{
        val user = db.userDAO().getUser()
        return db.pokemonDAO().getRecentPokemonById(idApi,user.id)
    }
}