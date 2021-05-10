package com.example.pokepedia.modelos

import com.google.gson.annotations.SerializedName

data class PokemonResponse (
    val count: Long,
    val next: String,
    val previous: Any? = null,
    @SerializedName("results") val pokemos: List<Pokemon>
) {}

