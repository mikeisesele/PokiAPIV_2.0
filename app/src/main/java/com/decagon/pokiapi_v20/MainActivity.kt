package com.decagon.pokiapi_v20

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.decagon.pokiapi_v20.databinding.ActivityMainBinding
import com.decagon.pokiapi_v20.model.PokemonDataResult
import com.decagon.pokiapi_v20.utils.CLickListener
import com.decagon.pokiapi_v20.utils.ConnectivityLiveData
import com.decagon.pokiapi_v20.utils.RetrofitClient
import com.decagon.pokiapi_v20.utils.toast
import com.decagon.pokiapi_v20.view.activities.DetailsActivity
import com.decagon.pokiapi_v20.view.adapters.PokemonListAdapter
import com.decagon.pokiapi_v20.viewmodel.PokemonViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity(), CLickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var Listadapter: PokemonListAdapter
    private lateinit var viewModel: PokemonViewModel
    private lateinit var database: List<PokemonDataResult>
    private var queryValue: Int? = null
    private var compositeDisposable = CompositeDisposable()
    private lateinit var pokemonListXml: RecyclerView
    private lateinit var connectivityLiveData: ConnectivityLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // initialize binding
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        connectivityLiveData = ConnectivityLiveData(application)

        connectivityLiveData.observe(this, { isAvailable ->
            //2
            when (isAvailable) {
                true -> {
                    toast("Network Available")
                    loadAllPokimon()
                }
                false -> {
                    toast("Network not available")
                }
            }
        })

        // initialize view model
        viewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)

        viewModel.pokimonList.observe(
            this, {

                database = it

                Listadapter.setPokemonData(database)
                Listadapter.notifyDataSetChanged()
            }
        )

        pokemonListXml = findViewById(R.id.poke_recycler_view)

        binding.floatingAsetLimitButton.setOnClickListener{
            try{
                if(binding.editPokemonListSize.text.isNotBlank()){
                    queryValue = binding.editPokemonListSize.text.toString().trim().toInt()
                    processQueryData(queryValue!!)
                } else {
                    Toast.makeText(this, "No query range provided", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception){
                Log.i("TAG", e.message.toString())
            }
        }
    }

    // function to get the data from the API and set it to a recycler adapter to populate the recycler view
    private fun loadAllPokimon(){
        try {
            compositeDisposable.add(
                RetrofitClient.retroAPIservice.getPokemonData()
                    // schedule the data gotten to display on the main thread
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Listadapter = PokemonListAdapter(this@MainActivity, this@MainActivity)

                        viewModel.pokimonList.value = it.results

                        pokemonListXml.adapter = Listadapter

                        pokemonListXml.layoutManager = LinearLayoutManager(this@MainActivity)
                    }
            )
        } catch (e: Exception){
            e.message?.let { toast(it) }
        }
    }

    private fun processQueryData(query: Int){
        try{
            compositeDisposable.add(
                RetrofitClient.retroAPIservice.queryRange(query, 0)
                    // schedule the data gotten to display on the main thread
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { pokemonDex ->
                        Listadapter = PokemonListAdapter(this@MainActivity, this@MainActivity)

                        database = pokemonDex.results

                        Listadapter.setPokemonData(database)
                        Listadapter.notifyDataSetChanged()
                        pokemonListXml.adapter = Listadapter
                        pokemonListXml.layoutManager = LinearLayoutManager(this@MainActivity)
                    }
            )
        } catch (e: Exception){
            e.message?.let { toast(it) }
        }
    }

    companion object{
        const val BASE_URL = "https://pokeapi.co/api/v2/"
    }

        override fun onItemClicked(position: Int) {
            val intent = Intent(this, DetailsActivity::class.java)
                intent.putExtra("id", position.toString())
                startActivity(intent)
    }
}