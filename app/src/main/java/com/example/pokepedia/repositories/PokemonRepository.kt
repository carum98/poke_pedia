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

    fun insertPokemonFavorito(Pokemon: PokemonEntity){
            db.pokemonDAO().insertPokemonFavorito(Pokemon)
    }
    fun insertPokemonRecent(Pokemon: PokemonRecentsEntity){
        db.pokemonDAO().insertRecentPokemon(Pokemon)
    }
    fun updatePokemonRecent(Pokemon: PokemonRecentsEntity){
        db.pokemonDAO().updateRecents(Pokemon)
    }
    fun deleteFavoritePokemon(idApi: String){
        db.pokemonDAO().deletePokemonFavorito(idApi)
    }
    fun getAllRecentsPokemon():LiveData<List<PokemonRecentsEntity>>  = db.pokemonDAO().getAllRecentsPokemon()
    fun getAllFavoritePokemon():LiveData<List<PokemonEntity>>  = db.pokemonDAO().getAllFavoritePokemon()
    fun getFavoritePokemonById(idApi:String):LiveData<PokemonEntity>  = db.pokemonDAO().getFavoritePokemon(idApi)
    fun getFavoritePokemonByName(name:String):LiveData<PokemonEntity>  = db.pokemonDAO().getFavoritePokemonByName(name)
    fun getRecentPokemonById(idApi:String):LiveData<PokemonRecentsEntity>  = db.pokemonDAO().getRecentPokemonById(idApi)
}