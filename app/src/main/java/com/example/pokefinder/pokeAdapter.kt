package com.example.pokefinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class pokeAdapter (private val pokelist: List<String>) : RecyclerView.Adapter<pokeAdapter.PokeViewHolder>() {

    class PokeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val pokeImage : ImageView

        init {
            pokeImage = view.findViewById(R.id.poke_image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.poke_item, parent, false)

        return PokeViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokeViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(pokelist[position])
            .centerCrop()
            .into(holder.pokeImage)
    }

    override fun getItemCount() = pokelist.size

}