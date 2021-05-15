package com.example.pokepedia.fragments

import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pokepedia.R
import com.example.pokepedia.R.color.yellow
import com.example.pokepedia.databinding.FragmentDetailBinding
import com.example.pokepedia.db.entities.PokemonEntity
import com.example.pokepedia.modelos.Pokemon
import com.example.pokepedia.viewmodels.PokemonDetailViewModel

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModelDetail:PokemonDetailViewModel by viewModels()
    private var isFavorite:Boolean=false
    private lateinit var pokemon:Pokemon
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        val view = binding.root
        pokemon = args.pokemon

        binding.elNombre.text = pokemon.name
        binding.laDescripcion.text = pokemon.url

        Glide.with(view.context)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pokemon.id}.png")
            .into(binding.elPokenon)
//        val viewModel = PokemonViewModel()



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelDetail.getFavoritePokemonForId(pokemon.id).observe(viewLifecycleOwner) {
            isFavorite = (it != null)
            binding.btnFavorito.setOnClickListener {
                var laEntidad = PokemonEntity(idApi = pokemon.id, nombre = pokemon.name)
                if (isFavorite) {
                    viewModelDetail.deleteFavoritePokemon(laEntidad.idApi)
                    isFavorite = false
                } else {
                    viewModelDetail.insertFavoritePokemon(laEntidad)
                    isFavorite = true
                }
            }
            btnState(isFavorite)
        }
    }
    fun btnState(state:Boolean){
        if (state){
            binding.btnFavorito.setBackgroundColor(Color.YELLOW)
        }else{
            binding.btnFavorito.setBackgroundColor(Color.GRAY)
        }

         //   binding.btnFavorito.isEnabled=!state
    }

}