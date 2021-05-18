package com.decagon.pokiapi_v20.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.decagon.pokiapi_v20.model.PokemonDataResult

class PokemonViewModel : ViewModel(){

    val pokimonList: MutableLiveData<List<PokemonDataResult>> by lazy {
        // initialize mutable live data on the list to be watched
        MutableLiveData<List<PokemonDataResult>>()
    }
}