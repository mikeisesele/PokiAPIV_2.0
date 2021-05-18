package com.decagon.pokiapi_v20.view.activities

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.decagon.pokiapi_v20.R
import com.decagon.pokiapi_v20.databinding.ActivityDetailsBinding
import com.decagon.pokiapi_v20.utils.RetrofitClient
import com.decagon.pokiapi_v20.view.adapters.AbilityRecyclerViewAdapter
import com.decagon.pokiapi_v20.view.adapters.MoveRecyclerViewAdapter
import com.decagon.pokiapi_v20.view.adapters.StatRecyclerViewAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailsActivity : AppCompatActivity() {

    private var compositeDisposable = CompositeDisposable()
    lateinit var binding: ActivityDetailsBinding

    lateinit var moveRecyclerViewXml: RecyclerView
    lateinit var statRecyclerViewXml : RecyclerView
    lateinit var abilityRecyclerViewXml:  RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val receiver: Bundle = intent.extras ?: return

        val pos = receiver.getString("id")

        compositeDisposable.add(
            RetrofitClient.retroAPIservice.getPokemonDetails("pokemon/$pos")
                // schedule the data gotten to display on the main thread
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {

                    Log.d("msg", it.toString())

                    binding.pokemonName.text = it.species.name

                    moveRecyclerViewXml = findViewById(R.id.move_recycler_view)
                    statRecyclerViewXml = findViewById(R.id.stat_recycler_view)
                    abilityRecyclerViewXml = findViewById(R.id.ability_recycler_view)

                    val statAdapter = StatRecyclerViewAdapter(it.stats)
                    val moveAdapter = MoveRecyclerViewAdapter(it.moves)
                    val abilityAdapter = AbilityRecyclerViewAdapter(it.abilities)

                    statRecyclerViewXml.adapter = statAdapter
                    moveRecyclerViewXml.adapter = moveAdapter
                    abilityRecyclerViewXml.adapter = abilityAdapter

                    //       set layout managers for adapters
                    moveRecyclerViewXml.layoutManager = LinearLayoutManager(this)
                    statRecyclerViewXml.layoutManager = LinearLayoutManager(this)
                    abilityRecyclerViewXml.layoutManager = LinearLayoutManager(this)

                    // use the glide library to fetch avatar and display it
                    Glide.with(this@DetailsActivity)
                        .load(it.sprites.back_default)
                        .into(binding.detailAvatar)
                }
        )
    }
}

