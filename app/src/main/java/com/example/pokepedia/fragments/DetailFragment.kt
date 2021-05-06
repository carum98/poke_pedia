package com.example.pokepedia.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.pokepedia.R
import com.example.pokepedia.databinding.FragmentDetailBinding
import com.example.pokepedia.databinding.FragmentHomeBinding
import com.example.pokepedia.modelos.Pokemon

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val losArgumentos: DetailFragmentArgs by navArgs()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var losPokemonesCompletos: List<Pokemon>
    private lateinit  var elPokemonActual: Pokemon
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        losPokemonesCompletos=obtenerLosPokemonesIniciales()
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        elPokemonActual= losPokemonesCompletos.first {it.id==losArgumentos.pokemon.id }
//        binding.elNombre.text = elPokemonActual.nombre
//        binding.laDescripcion.text =elPokemonActual.descripcion
//        var laEvolucion: Pokemon = GestioneLaEvolucion()
//        Glide.with(binding.elPokenon.context)
//            .load(losArgumentos.pokemon.fotoURL)
//            .into(binding.elPokenon)
//        binding.Evolucion.setOnClickListener{
//            var action = DetailFragmentDirections.actiondetailfragmentself(laEvolucion)
//            binding.root.findNavController().navigate(action)
//        }
    }

//    private fun GestioneLaEvolucion(): Pokemon {
////        var elNombreDeLaEvolucion: String
////        var laEvolucion: Pokemon = losPokemonesCompletos.first { it.id == elPokemonActual.idEvolucion }
////        if (laEvolucion.id == losArgumentos.pokemon.id) {
////            elNombreDeLaEvolucion = "No tiene evolución"
////            binding.Evolucion.visibility = View.GONE
////        } else {
////            elNombreDeLaEvolucion = "Evoluciona a: " + laEvolucion.nombre
////            binding.Evolucion.visibility = View.VISIBLE
////        }
////        binding.textView5.text = elNombreDeLaEvolucion
//
//     return laEvolucion
//    }

    override fun onStart() {
//        losPokemonesCompletos=obtenerLosPokemonesIniciales()
        super.onStart()
    }
    override fun onResume() {
//        losPokemonesCompletos=obtenerLosPokemonesIniciales()
        super.onResume()
    }

//    private  fun obtenerLosPokemonesIniciales() : List<Pokemon> {
//        return mutableListOf(
//            Pokemon(1, "Bulbasaur",
//                "https://i.pinimg.com/originals/3b/78/47/3b7847675982776e5219e12a680ecd84.png",
//                "el Pokémon Semilla, es uno de los Pokémon iniciales de la región de Kanto.",
//                2),
//            Pokemon(2, "Ivysaur",
//                "https://images.wikidexcdn.net/mwuploads/wikidex/8/86/latest/20190406151903/Ivysaur.png",
//                "es un Pokémon de tipo planta y veneno. Es la segunda etapa de Bulbasaur y al evolucionar se volvió un poco más serio.",
//                3),
//            Pokemon(3, "Venusaur",
//                "https://upload.wikimedia.org/wikipedia/en/2/20/Pokémon_Venusaur_art.png",
//                "es un Pokémon de tipo planta y veneno. Es la tercera y última etapa de Bulbasaur. ",
//                3),
//            Pokemon(4, "Charmander",
//                "https://assets.pokemon.com/assets/cms2/img/pokedex/full/004.png",
//                "es una salamandra bípeda, y uno de los Pokémon iniciales a elegir por el jugador en la región de Kanto.",
//                5),
//            Pokemon(5, "Charmeleon",
//                "https://images.wikidexcdn.net/mwuploads/wikidex/f/fb/latest/20200411222755/Charmeleon.png",
//                "es un Pokémon salamandra bípedo. Es la segunda etapa de Charmander",
//                6),
//            Pokemon(6, "Charizard",
//                "https://static.wikia.nocookie.net/estrategiaspokemon/images/9/95/Charizard.png/revision/latest?cb=20151022213439&path-prefix=es",
//                "Es la tercera y última etapa de evolución de Charmander. Es uno de los Pokémon más conocidos. Aparece por primera vez en Pokémon Red y Blue.",
//                6)
//        )
//    }
}