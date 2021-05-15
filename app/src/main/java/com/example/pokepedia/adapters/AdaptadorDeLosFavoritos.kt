package com.example.pokepedia.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.pokepedia.R
import com.example.pokepedia.databinding.FragmentFavoritosBinding
import com.example.pokepedia.databinding.FragmentPrincipalBinding
import com.example.pokepedia.fragments.ListaDeFavoritosFragmentDirections
import com.example.pokepedia.fragments.ListaPrincipalFragmentDirections

import com.example.pokepedia.modelos.Pokemon

class AdaptadorDeLosFavoritos
: RecyclerView.Adapter<AdaptadorDeLosFavoritos.ListaFavoritosViewHolder>() {
    var losPokemones: List<Pokemon> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaFavoritosViewHolder {
        val binding = FragmentFavoritosBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        return ListaFavoritosViewHolder(binding)
    }
    inner class ListaFavoritosViewHolder(private val binding: FragmentFavoritosBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon:Pokemon){
            binding.content.text = pokemon.name

            Glide.with(binding.laFotoDelPokemon.context)
                .load(pokemon.urlImagen)
                .circleCrop()
                .into(binding.laFotoDelPokemon)

            binding.elItemDeFavoritos.setOnClickListener {
                var action = ListaDeFavoritosFragmentDirections.actionListaDeFavoritosFragmentToDetailFragment(
                    pokemon
                )
                binding.content.findNavController().navigate(action)
            }
        }
    }
    override fun getItemCount(): Int = losPokemones.size

    override fun onBindViewHolder(holder: AdaptadorDeLosFavoritos.ListaFavoritosViewHolder, position: Int) {
        holder.bind(losPokemones[position])
    }
}