package com.decagon.pokiapi_v20.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.decagon.pokiapi_v20.R
import com.decagon.pokiapi_v20.model.pokemonattributes.Ability

class AbilityRecyclerViewAdapter(var AbilityList: List<Ability>) : RecyclerView.Adapter<AbilityRecyclerViewAdapter.AbilityDetailsViewHolder>() {

    // create the views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilityDetailsViewHolder {
        val statContent = LayoutInflater.from(parent.context).inflate(R.layout.details_list_content, parent, false)
        return AbilityDetailsViewHolder(statContent)
    }

    // get the model xml layout to be recycled
    inner class AbilityDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var AbilityHolder: TextView = itemView.findViewById(R.id.list_content_view)
        fun bind(AbilityList: List<Ability>, position: Int) {
            AbilityHolder.text = AbilityList[position].ability.name
        }
    }

    // bind each instance of xml to data
    override fun onBindViewHolder(holder: AbilityDetailsViewHolder, position: Int) {
        holder.bind(AbilityList, position)
    }

    // get list count
    override fun getItemCount(): Int {
        return AbilityList.size
    }
}
