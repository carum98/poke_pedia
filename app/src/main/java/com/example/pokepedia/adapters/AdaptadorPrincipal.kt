package com.example.pokepedia.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.pokepedia.R
import com.example.pokepedia.fragments.ListaPrincipalFragmentDirections

import com.example.pokepedia.modelos.Pokemon
import com.example.pokepedia.widgets.EmptyView

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}

class AdaptadorPrincipal() : RecyclerView.Adapter<BaseViewHolder<*>>() {

    var losPokemones: List<Pokemon> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    companion object {
        private const val VIEW_TYPE_EMPTY = 0
        private const val VIEW_TYPE_ITEM = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_principal, parent, false)

        return when (viewType) {
            VIEW_TYPE_EMPTY -> EmptyViewHolder(view)
            VIEW_TYPE_ITEM -> ViewHolder(view)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }


    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is EmptyViewHolder -> holder.bind("No hay pokemons")
            is ViewHolder -> holder.bind(losPokemones[position])
            else -> throw IllegalArgumentException("Invalid ViewHolder")
        }
    }

    override fun getItemCount(): Int = losPokemones.size

    override fun getItemViewType(position: Int): Int = if (losPokemones.count() == 0) {
        VIEW_TYPE_EMPTY
    } else {
        VIEW_TYPE_ITEM
    }

    inner class ViewHolder(view: View) : BaseViewHolder<Pokemon>(view) {
        private val urlView: TextView = view.findViewById(R.id.urlMain)
        private val contentView: TextView = view.findViewById(R.id.content)
        private val idView: TextView = view.findViewById(R.id.idMain)

        override fun bind(item: Pokemon) {
            item.id = item.url.split('/')[6]

            urlView.text = item.url
            contentView.text = item.name
            idView.text = item.id

            Glide.with(itemView.context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${item.id}.png")
                .circleCrop()
                .into(itemView.findViewById(R.id.laFotoDelPokemon))

            itemView.setOnClickListener {
                val action = ListaPrincipalFragmentDirections.actionListaPrincipalFragmentToDetailFragment(
                    item
                )
                itemView.findNavController().navigate(action)
            }
        }
    }

    inner class EmptyViewHolder(view: View) : BaseViewHolder<String>(view) {
//        private val empty: EmptyView = itemView.findViewById(R.id.empty_list)

        override fun bind(item: String) {
//            empty.visibility = View.VISIBLE
        }
    }
}