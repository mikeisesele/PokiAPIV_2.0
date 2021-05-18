package com.decagon.pokiapi_v20.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.decagon.pokiapi_v20.R
import com.decagon.pokiapi_v20.model.pokemonattributes.Move


class MoveRecyclerViewAdapter(var moveList: List<Move>) : RecyclerView.Adapter<MoveRecyclerViewAdapter.MoveDetailsViewHolder>() {

        // create the views
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoveDetailsViewHolder {
            val statContent = LayoutInflater.from(parent.context).inflate(R.layout.details_list_content, parent, false)
            return MoveDetailsViewHolder(statContent)
        }

        // get the model xml layout to be recycled
        inner class MoveDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var moveHolder: TextView = itemView.findViewById(R.id.list_content_view)
            fun bind(moveList: List<Move>, position: Int) {
                moveHolder.text = moveList[position].move.name
            }
        }

        // bind each instance of xml to data
        override fun onBindViewHolder(holder: MoveDetailsViewHolder, position: Int) {
            holder.bind(moveList, position)
        }

        // get list count
        override fun getItemCount(): Int {
            return moveList.size
        }
    }
