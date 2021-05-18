package com.decagon.pokiapi_v20.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.decagon.pokiapi_v20.R
import com.decagon.pokiapi_v20.model.PokemonDataResult
import com.decagon.pokiapi_v20.utils.CLickListener

class PokemonListAdapter(var context: Context, var clickListener: CLickListener) : RecyclerView.Adapter<PokemonListAdapter.PokemonModelViewHolder>() {


    var pokiRecyclerList: List<PokemonDataResult> = listOf()

    // inflates the model item to be recycled
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonModelViewHolder {
        // creates the viewHolders that holds the data to be displayed
        val recyclerLayout = LayoutInflater.from(parent.context).inflate(R.layout.pokimon_list_model , parent, false)
        return PokemonModelViewHolder(recyclerLayout)
    }

    // inner class PokemnViewHolder takes the view id type to be shown, and places it in the view holder
    inner class PokemonModelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val pokemonName: TextView = itemView.findViewById(R.id.poke_name)
        val pokemonImage: ImageView = itemView.findViewById(R.id.poke_avatar)
        var card: CardView = itemView.findViewById(R.id.cardView)

        override fun onClick(v: View?) {
            clickListener.onItemClicked(adapterPosition)
        }
    }

    fun setPokemonData(recyclerList: List<PokemonDataResult>) {
        this.pokiRecyclerList = recyclerList
    }

    // binds data to each view
    override fun onBindViewHolder(holder: PokemonModelViewHolder, position: Int) {
        var pos = position + 1
        holder.pokemonName.text = pokiRecyclerList[position].name
        val pokemonImage = holder.pokemonImage
        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pos}.png")
                .into(pokemonImage)


        holder.card.setOnClickListener {
            clickListener.onItemClicked(pos)
        }
    }

    override fun getItemCount(): Int {
        return pokiRecyclerList.size
    }
}

