package com.example.pokepedia.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.pokepedia.db.DataBase
import androidx.room.RoomDatabase
import com.example.pokepedia.db.entities.PokemonEntity

class PokemonRepository(context: Context) {
    private  val db:DataBase = DataBase.getDatabase(context)

    fun insertPokemonFavorito(Pokemon: PokemonEntity){
            db.pokemonDAO().insertPokemonFavorito(Pokemon)
    }
    fun deleteFavoritePokemon(idApi: String){
        db.pokemonDAO().deletePokemonFavorito(idApi)
    }
    fun getAllFavoritePokemon():LiveData<List<PokemonEntity>>  = db.pokemonDAO().getAllFavoritePokemon()
    fun getFavoritePokemonForId(idApi:String):LiveData<PokemonEntity>  = db.pokemonDAO().getFavoritePokemon(idApi)

}