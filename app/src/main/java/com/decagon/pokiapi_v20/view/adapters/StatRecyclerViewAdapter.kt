package com.decagon.pokiapi_v20.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.decagon.pokiapi_v20.R
import com.decagon.pokiapi_v20.model.pokemonattributes.Stat

class StatRecyclerViewAdapter(var statList: List<Stat>) : RecyclerView.Adapter<StatRecyclerViewAdapter.StatDetailsViewHolder>() {

        // create the views
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatDetailsViewHolder {
            val statContent = LayoutInflater.from(parent.context).inflate(R.layout.details_list_content, parent, false)
            return StatDetailsViewHolder(statContent)
        }

        // get the model xml layout to be recycled
        inner class StatDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var statHolder: TextView = itemView.findViewById(R.id.list_content_view)
            fun bind(statList: List<Stat>, position: Int) {
                statHolder.text = statList[position].stat.name
            }
        }

        // bind each instance of xml to data
        override fun onBindViewHolder(holder: StatDetailsViewHolder, position: Int) {
            holder.bind(statList, position)
        }

        // get list count
        override fun getItemCount(): Int {
            return statList.size
        }
    }