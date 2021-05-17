package com.example.pokepedia.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.pokepedia.db.DataBase
import androidx.room.RoomDatabase
import com.example.pokepedia.db.entities.PokemonEntity

class PokemonRepository(context: Context) {
    private val db:DataBase = DataBase.getDatabase(context)

    fun insertPokemonFavorito(pokemon: PokemonEntity){
        val user = db.userDAO().getUser()
        pokemon.userId = user.id
        db.pokemonDAO().insertPokemonFavorito(pokemon)
    }
    fun deleteFavoritePokemon(idApi: String){
        db.pokemonDAO().deletePokemonFavorito(idApi)
    }
    fun getAllFavoritePokemon():LiveData<List<PokemonEntity>> {
        val user = db.userDAO().getUser()
        return db.pokemonDAO().getAllFavoritePokemon(userId = user.id)
    }
    fun getFavoritePokemonById(idApi:String):LiveData<PokemonEntity>  = db.pokemonDAO().getFavoritePokemon(idApi)
    fun getFavoritePokemonByName(name:String):LiveData<PokemonEntity>  = db.pokemonDAO().getFavoritePokemonByName(name)
}