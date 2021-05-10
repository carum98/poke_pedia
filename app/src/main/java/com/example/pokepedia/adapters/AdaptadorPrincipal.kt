package com.example.pokepedia.adapters

import android.opengl.Visibility
import android.text.Layout
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.solver.widgets.WidgetContainer
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.pokepedia.R
import com.example.pokepedia.databinding.FragmentListaPrincipalBinding
import com.example.pokepedia.databinding.FragmentPrincipalItemBinding
import com.example.pokepedia.fragments.ListaPrincipalFragmentDirections

import com.example.pokepedia.modelos.Pokemon

class AdaptadorPrincipal : RecyclerView.Adapter<AdaptadorPrincipal.ListaPrincipalViewHolder>() {


    var losPokemones: List<Pokemon> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaPrincipalViewHolder {
        val binding = FragmentPrincipalItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        return ListaPrincipalViewHolder(binding)
    }
    inner class ListaPrincipalViewHolder(private val binding:FragmentPrincipalItemBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun bind(pokemon:Pokemon){
                binding.pokemonName.text = pokemon.name
                binding.idMain.text = pokemon.id

                Glide.with(binding.elitem.context)
                    .load(pokemon.urlImagen)
                    .circleCrop()
                    .into(binding.elitem.findViewById(R.id.laFotoDelPokemon))

               /* bindingPrincipal.botonMostrar.setOnClickListener{



                 *//*   var action = ListaPrincipalFragmentDirections.actionListaPrincipalFragmentToEmptyFragment(
                        R.drawable.pokemo_no_encontrado,"Pokemon no Encontrado 1"
                    )
                    holder.itemView.findNavController().navigate(action)*//*
                }*/
                binding.elitem.setOnClickListener {
                    var action = ListaPrincipalFragmentDirections.actionListaPrincipalFragmentToDetailFragment(
                        losPokemones.first{ it.id == binding.idMain.text}
                    )
                    binding.elitem.findNavController().navigate(action)
                }
            }
    }
    override fun onBindViewHolder(holder: ListaPrincipalViewHolder, position: Int) {
        holder.bind(losPokemones[position])
    }
 /*   override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//       val elPokemonActual = losPokemones[position]
 *//*       holder.urlView.text = elPokemonActual.url
        holder.pokemonName.text = elPokemonActual.name
        holder.idView.text = elPokemonActual.id
        Glide.with(holder.itemView.context)
            .load(elPokemonActual.urlImagen)
            .circleCrop()
            .into(holder.itemView.findViewById(R.id.laFotoDelPokemon))
        holder.botonPrueba.setOnClickListener{
            Log.d("Estoy adentro","elAppPokeIRvin")
            holder.busquedaFallida.visibility=View.VISIBLE
            var action = ListaPrincipalFragmentDirections.actionListaPrincipalFragmentToEmptyFragment(
                R.drawable.pokemo_no_encontrado,"Pokemon no Encontrado 1"
            )
            holder.itemView.findNavController().navigate(action)
        }
        holder.itemView.setOnClickListener {
            var action = ListaPrincipalFragmentDirections.actionListaPrincipalFragmentToDetailFragment(
                losPokemones.first{ it.id == holder.idView.text}
            )
            holder.itemView.findNavController().navigate(action)
        }*//*
        holder.bind(losPokemones[position])
    }*/

    override fun getItemCount(): Int = losPokemones.size

   /* inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val urlView: TextView = view.findViewById(R.id.urlMain)
        val pokemonName: TextView = view.findViewById(R.id.pokemonName)
        val idView: TextView = view.findViewById(R.id.idMain)
        val botonPrueba: TextView = view.findViewById(R.id.botonMostrar)
          val busquedaFallida: View= view.findViewById(R.id.BusquedaFallida)
        val item:View=view.findViewById(R.id.elitem)
     *//*   override fun toString(): String {
            return super.toString() + " '" + pokemonName.text + "'"
        }*//*
    }*/
}