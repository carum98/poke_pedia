package com.example.pokepedia.modelos

data class PokemonResponse (
    val count: Long,
    val next: String,
    val previous: Any? = null,
    val pokemos: List<Pokemon>
) {}

