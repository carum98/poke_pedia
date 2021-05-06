package com.example.pokepedia.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.pokepedia.R
import com.example.pokepedia.fragments.ListaPrincipalFragmentDirections

import com.example.pokepedia.modelos.Pokemon

class AdaptadorPrincipal() : RecyclerView.Adapter<AdaptadorPrincipal.ViewHolder>() {


    var losPokemones: List<Pokemon> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_principal, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

       val item = losPokemones[position]
       holder.urlView.text = item.url
       holder.contentView.text = item.name
//        Glide.with(holder.itemView.context)
//            .load(item.fotoURL)
//            .circleCrop()
//            .into(holder.itemView.findViewById(R.id.laFotoDelPokemon))

        //val action = LoginFragmentDirections.actionLoginFragmentToDetailFragment(
          //      User("Heriberto", "Urena")
           //)

        holder.itemView.setOnClickListener {
//            var  elIdDelPokemon =   holder.idView.text.toString().toInt()
            var action = ListaPrincipalFragmentDirections.actionListaPrincipalFragmentToDetailFragment(
                losPokemones[position]
            )
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int = losPokemones.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val urlView: TextView = view.findViewById(R.id.urlMain)
        val contentView: TextView = view.findViewById(R.id.content)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}