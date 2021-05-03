package com.example.pokepedia.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.pokepedia.R

import com.example.pokepedia.modelos.Pokemon

class AdaptadorPrincipal(
    private val losPokemones: List<Pokemon>
) : RecyclerView.Adapter<AdaptadorPrincipal.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_principal, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = losPokemones[position]
        holder.idView.text = item.id.toString()
        holder.contentView.text = item.nombre

        holder.itemView.setOnClickListener {
            holder.itemView.findNavController().navigate(R.id.action_itemFragment_to_detailFragment)
        }
    }

    override fun getItemCount(): Int = losPokemones.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_number)
        val contentView: TextView = view.findViewById(R.id.content)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}