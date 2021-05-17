package com.example.pokepedia.viewmodels

import androidx.lifecycle.AndroidViewModel
import android.app.Application
import androidx.lifecycle.LiveData
import com.example.pokepedia.db.entities.PokemonEntity
import com.example.pokepedia.repositories.PokemonRepository


class PokemonDetailViewModel(application:Application): AndroidViewModel(application) {
    val  repository = PokemonRepository(application.applicationContext)

    fun getFavoritePokemon():LiveData<List<PokemonEntity>>{
        return  repository.getAllFavoritePokemon()
    }
    fun getFavoritePokemonById(id:String):LiveData<PokemonEntity>{
        return repository.getFavoritePokemonById(id)
    }
    fun getFavoritePokemonByName(nombre:String):LiveData<PokemonEntity>{
        return repository.getFavoritePokemonByName(nombre)
    }
    fun insertFavoritePokemon(pokemon:PokemonEntity){
            repository.insertPokemonFavorito(pokemon)
    }
    fun deleteFavoritePokemon(idApi:String){
        repository.deleteFavoritePokemon(idApi)
    }

}