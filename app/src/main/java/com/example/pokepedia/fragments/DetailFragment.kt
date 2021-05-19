package com.example.pokepedia.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pokepedia.R
import com.example.pokepedia.adapters.AdaptadorDeRecientes
import com.example.pokepedia.adapters.AdaptadorDetalle
import com.example.pokepedia.databinding.FragmentDetailBinding
import com.example.pokepedia.db.emtities.PokemonRecentsEntity
import com.example.pokepedia.db.entities.PokemonEntity
import com.example.pokepedia.modelos.DetalleDeCadena
import com.example.pokepedia.modelos.Pokemon
import com.example.pokepedia.modelos.PokemonDetail
import com.example.pokepedia.viewmodels.PokemonDetailViewModel
import com.example.pokepedia.viewmodels.PokemonViewModel


class DetailFragment : Fragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModelDetail:PokemonDetailViewModel by viewModels()
    private val viewModel: PokemonViewModel by viewModels()
    private var isFavorite:Boolean=false
    private lateinit var pokemon:Pokemon
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var pokemonDetail:PokemonDetail
    private  val adapter = AdaptadorDetalle()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        pokemon = args.pokemon
        binding.elNombre.text = pokemon.name.capitalize()
        viewModel.getPokemonDetail(pokemon.id)
        viewModel.getDataPokemonDetail().observe(viewLifecycleOwner)
        {
            pokemonDetail = it
            binding.laDescripcion.text = pokemonDetail.flavor_text_entries.firstOrNull{ detail -> detail.language.name == "es"}?.flavor_text
                ?: ""
            binding.tipo.text = pokemonDetail.genera.firstOrNull{tipo -> tipo.language.name == "es"}?.genus?:""
            getEvolutionChain()
        }
        Glide.with(view.context)
            .load(pokemon.getImage())
            .into(binding.elPokenon)
        return view
    }


    private fun getEvolutionChain() {
        var elEvolutionChainUrl = pokemonDetail.evolution_chain.url.split("/")
        var elIdDeLaCadena = elEvolutionChainUrl[elEvolutionChainUrl.size - 2]
        viewModel.getPokemonEvolution(elIdDeLaCadena)
        viewModel.getPokemonEvolutionData().observe(viewLifecycleOwner)
        {
            var evolutions=it.chain
            pokemon.evoluciones= arrayListOf()
            adapter.losPokemones = pokemon.evoluciones!!
            binding.laListaDeEvoluciones.adapter = adapter
            MostrarResultado (evolutions.evolves_to.isEmpty())
            findEvolutions(evolutions)
        }

    }

    private fun findEvolutions(evolutions: DetalleDeCadena) {
        var elArregloDelId=evolutions.species.url.split("/")
        var elIdDelPokemonParaAgregar=elArregloDelId[elArregloDelId.size-2]
        if(!pokemon.id.equals(elIdDelPokemonParaAgregar)){
            addEvolutions(evolutions.species.name,elIdDelPokemonParaAgregar)
        }
        evolutions.evolves_to.forEach{
            findEvolutions(it)
        }
    }
    private fun MostrarResultado(seDebeMostrar:Boolean) {
        if(!seDebeMostrar){
            binding.noHayPokemon.visibility = View.GONE
            binding.laListaDeEvoluciones.visibility =View.VISIBLE
        }else{
            binding.noHayPokemon.visibility = View.VISIBLE
            binding.laListaDeEvoluciones.visibility =View.GONE
        }
    }
    private fun addEvolutions(pokemonName:String, idDelPokemon:String) {
        pokemon.evoluciones?.add(Pokemon(idDelPokemon,pokemonName,"",null))
        adapter.losPokemones = pokemon.evoluciones!!
        binding.laListaDeEvoluciones.adapter = adapter

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelDetail.getRecentPokemonById(pokemon.id).observe(viewLifecycleOwner) {
            if(it != null){
                var laEntidadDeRecientes = it
                laEntidadDeRecientes.timeStamp= System.currentTimeMillis()
                viewModelDetail.updatePokemonRecent(laEntidadDeRecientes)
            }else{
                var laEntidadDeRecientes = PokemonRecentsEntity(idApi = pokemon.id, nombre = pokemon.name, timeStamp = System.currentTimeMillis(), userId = 0)
                viewModelDetail.insertPokemonRecent(laEntidadDeRecientes)
            }
        }
        viewModelDetail.getFavoritePokemonById(pokemon.id).observe(viewLifecycleOwner) {
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
            binding.btnFavorito.setTextColor(Color.BLACK)
            val icon = resources.getDrawable(R.drawable.ic_favorite_black)
            binding.btnFavorito.setCompoundDrawablesRelativeWithIntrinsicBounds(icon,null,null,null)
        }else{
            binding.btnFavorito.setBackgroundColor(Color.BLACK)
            binding.btnFavorito.setTextColor(Color.WHITE)
            val icon = resources.getDrawable(R.drawable.ic_favorite)
            binding.btnFavorito.setCompoundDrawablesRelativeWithIntrinsicBounds(icon,null,null,null)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        pokemon.evoluciones= arrayListOf()
        _binding = null
    }

}