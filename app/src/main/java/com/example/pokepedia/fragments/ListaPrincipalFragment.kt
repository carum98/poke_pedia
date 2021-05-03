package com.example.pokepedia.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokepedia.adapters.AdaptadorPrincipal
import com.example.pokepedia.R
import com.example.pokepedia.modelos.Pokemon

/**
 * A fragment representing a list of Items.
 */
class ListaPrincipalFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }
    private  fun obtenerLosPokemonesIniciales() : List<Pokemon> {
        return mutableListOf(
            Pokemon(1, "Bulbasaur",
                "https://i.pinimg.com/originals/3b/78/47/3b7847675982776e5219e12a680ecd84.png",
                "el Pokémon Semilla, es uno de los Pokémon iniciales de la región de Kanto.",
                2),
            Pokemon(2, "Ivysaur",
                "https://images.wikidexcdn.net/mwuploads/wikidex/8/86/latest/20190406151903/Ivysaur.png",
                "es un Pokémon de tipo planta y veneno. Es la segunda etapa de Bulbasaur y al evolucionar se volvió un poco más serio.",
                3),
            Pokemon(3, "Venusaur",
                "https://upload.wikimedia.org/wikipedia/en/2/20/Pokémon_Venusaur_art.png",
                "es un Pokémon de tipo planta y veneno. Es la tercera y última etapa de Bulbasaur. ",
                3),
            Pokemon(4, "Charmander",
                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/004.png",
                "es una salamandra bípeda, y uno de los Pokémon iniciales a elegir por el jugador en la región de Kanto.",
                5),
            Pokemon(5, "Charmeleon",
                "https://images.wikidexcdn.net/mwuploads/wikidex/f/fb/latest/20200411222755/Charmeleon.png",
                "es un Pokémon salamandra bípedo. Es la segunda etapa de Charmander",
                6),
            Pokemon(6, "Charizard",
                "https://static.wikia.nocookie.net/estrategiaspokemon/images/9/95/Charizard.png/revision/latest?cb=20151022213439&path-prefix=es",
                "Es la tercera y última etapa de evolución de Charmander. Es uno de los Pokémon más conocidos. Aparece por primera vez en Pokémon Red y Blue.",
                6)
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lista_principal, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                val losPokemonesIniciales = obtenerLosPokemonesIniciales()
                adapter = AdaptadorPrincipal(losPokemonesIniciales)
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
            ListaPrincipalFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}