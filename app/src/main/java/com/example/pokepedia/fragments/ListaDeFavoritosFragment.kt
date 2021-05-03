package com.example.pokepedia.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokepedia.adapters.AdaptadorDeLosFavoritos
import com.example.pokepedia.R
import com.example.pokepedia.modelos.Pokemon

/**
 * A fragment representing a list of Items.
 */
class ListaDeFavoritosFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }
    private  fun obtenerLosPokemonesFavoritos() : List<Pokemon> {
        return mutableListOf(
            Pokemon(2, "Ivysaur",
                "https://images.wikidexcdn.net/mwuploads/wikidex/8/86/latest/20190406151903/Ivysaur.png",
                "es un Pokémon de tipo planta y veneno. Es la segunda etapa de Bulbasaur y al evolucionar se volvió un poco más serio.",
                3),
            Pokemon(3, "Venusaur",
                "https://upload.wikimedia.org/wikipedia/en/2/20/Pokémon_Venusaur_art.png",
                "es un Pokémon de tipo planta y veneno. Es la tercera y última etapa de Bulbasaur. ",
                3),
            Pokemon(6, "Charizard",
                "https://static.wikia.nocookie.net/estrategiaspokemon/images/9/95/Charizard.png/revision/latest?cb=20151022213439&path-prefix=es",
                "Es la tercera y última etapa de evolución de Charmander. Es uno de los Pokémon más conocidos. Aparece por primera vez en Pokémon Red y Blue.",
                3)
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lista_favoritos, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                val losPokemonesFavoritos = obtenerLosPokemonesFavoritos()
                adapter = AdaptadorDeLosFavoritos(losPokemonesFavoritos)
            }
        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            ListaDeFavoritosFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}