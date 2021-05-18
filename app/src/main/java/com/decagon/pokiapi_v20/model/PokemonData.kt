package com.decagon.pokiapi_v20.model

data class PokemonData(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PokemonDataResult>
)
