package com.example.pokepedia.service

import com.example.pokepedia.modelos.Pokemon
import com.example.pokepedia.modelos.PokemonChain
import com.example.pokepedia.modelos.PokemonDetail
import com.example.pokepedia.modelos.PokemonResponse
import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {
    @GET("pokemon")
    fun getPokemonList(
        @Query("limit") limit: String? = null,
        @Query("offset") offset: String,
    ): Call<PokemonResponse>
    @GET("pokemon/{pokemon}")
    fun getPokemon(
        @Path("pokemon")  elPokemon: String? = null,
    ): Call<Pokemon>
    @GET("pokemon-species/{id}")
    fun getPokemonDetail(
        @Path("id") elPokemon: String? = null,
    ): Call<PokemonDetail>

    @GET("evolution-chain/{id}")
    fun getPokemonEvolution(
        @Path("id") elPokemon: String? = null,
    ): Call<PokemonChain>
}