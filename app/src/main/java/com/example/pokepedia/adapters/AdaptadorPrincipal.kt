package com.example.pokepedia.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.pokepedia.R
import com.example.pokepedia.databinding.FragmentPrincipalBinding
import com.example.pokepedia.fragments.ListaPrincipalFragmentDirections

import com.example.pokepedia.modelos.Pokemon
import com.example.pokepedia.widgets.EmptyView



class AdaptadorPrincipal : RecyclerView.Adapter<AdaptadorPrincipal.ListaPrincipalViewHolder>() {

    var losPokemones: List<Pokemon> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaPrincipalViewHolder {
        val binding = FragmentPrincipalBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        return ListaPrincipalViewHolder(binding)
    }
    inner class ListaPrincipalViewHolder(private val binding:FragmentPrincipalBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon:Pokemon){
            binding.content.text = pokemon.name.capitalize()

           Glide.with(binding.laFotoDelPokemon.context)
                .load(pokemon.urlImagen)
                .circleCrop()
                .into(binding.laFotoDelPokemon)

            binding.elItemDelPrincipal.setOnClickListener {
                var action = ListaPrincipalFragmentDirections.actionListaPrincipalFragmentToDetailFragment(
                    pokemon
                )
                binding.content.findNavController().navigate(action)
            }
        }
    }

    override fun getItemCount(): Int = losPokemones.size

    override fun onBindViewHolder(holder: ListaPrincipalViewHolder, position: Int) {
        holder.bind(losPokemones[position])
    }

}