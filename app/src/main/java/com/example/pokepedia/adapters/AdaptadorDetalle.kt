package com.example.pokepedia.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokepedia.databinding.FragmentDetailEvolutionBinding
import com.example.pokepedia.fragments.DetailFragmentDirections
import com.example.pokepedia.modelos.Pokemon

class AdaptadorDetalle : RecyclerView.Adapter<AdaptadorDetalle.AdaptadorDetalleViewHolder>() {

    var losPokemones: List<Pokemon> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorDetalleViewHolder {
        val binding = FragmentDetailEvolutionBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        return AdaptadorDetalleViewHolder(binding)
    }
    inner class AdaptadorDetalleViewHolder(private val binding: FragmentDetailEvolutionBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: Pokemon){
            binding.nombre.text = pokemon.name.capitalize()

            Glide.with(binding.laFotoDelPokemon.context)
                .load(pokemon.getImage())
                .circleCrop()
                .into(binding.laFotoDelPokemon)

            binding.elItemDeDetail.setOnClickListener {
                var action =  DetailFragmentDirections.actiondetailfragmentself(
                    pokemon
                )
                binding.elItemDeDetail.findNavController().navigate(action)
            }
        }
    }

    override fun getItemCount(): Int = losPokemones.size

    override fun onBindViewHolder(holder: AdaptadorDetalleViewHolder, position: Int) {
        holder.bind(losPokemones[position])
    }

}