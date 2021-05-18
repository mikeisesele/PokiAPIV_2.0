package com.decagon.pokiapi_v20.utils

import com.decagon.pokiapi_v20.model.PokemonData
import com.decagon.pokiapi_v20.model.PokemonDetails
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HttpRequests {

    // get a range of pokemon
    @GET("pokemon")
    fun queryRange(@Query("limit") limit: Int?, @Query("offset") offset: Int): Observable<PokemonData>

    //    // get specific url of each pokemon
    @GET("{url}")
    fun getPokemonDetails(@Path("url") url: String): Observable<PokemonDetails>

    // load pokemon on start
    @GET("pokemon/?offset=0&limit=500")
    fun getPokemonData(): Observable<PokemonData>
}
