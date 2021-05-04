package com.example.pokepedia.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.pokepedia.R
import com.example.pokepedia.fragments.ListaDeFavoritosFragmentDirections
import com.example.pokepedia.fragments.ListaPrincipalFragmentDirections

import com.example.pokepedia.modelos.Pokemon

class AdaptadorDeLosFavoritos(
    private val losPokemonesFavoritos: List<Pokemon>
) : RecyclerView.Adapter<AdaptadorDeLosFavoritos.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_favoritos, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = losPokemonesFavoritos[position]
        holder.idView.text = item.id.toString()
        holder.contentView.text = item.nombre
        Glide.with(holder.itemView.context)
                .load(item.fotoURL)
                .circleCrop()
                .into(holder.itemView.findViewById(R.id.laFotoDelPokemon))

        holder.itemView.setOnClickListener {
            var action = ListaDeFavoritosFragmentDirections.actionListaDeFavoritosFragmentToDetailFragment(
                losPokemonesFavoritos[position]
            )
            holder.itemView.findNavController().navigate(action)
//            holder.itemView.findNavController().navigate(R.id.action_itemFragment2_to_detailFragment)
        }
    }

    override fun getItemCount(): Int = losPokemonesFavoritos.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_number)
        val contentView: TextView = view.findViewById(R.id.content)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}