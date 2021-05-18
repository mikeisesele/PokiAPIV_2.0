package com.decagon.pokiapi_v20.model.pokemonattributes

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)
