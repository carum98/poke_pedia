package com.example.pokepedia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.pokepedia.BuildConfig
import com.example.pokepedia.adapters.AdaptadorDeRecientes
import com.example.pokepedia.databinding.FrangmentListaRecientesBinding
import com.example.pokepedia.modelos.Pokemon
import com.example.pokepedia.viewmodels.PokemonDetailViewModel

class ListaRecientesFragment : Fragment() {
    private var _binding: FrangmentListaRecientesBinding? = null
    private val binding get() = _binding!!
    private val viewModelDetail: PokemonDetailViewModel by viewModels()
    private lateinit var losPokemonesRecientes: MutableList<Pokemon>
    private  val adapter = AdaptadorDeRecientes()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FrangmentListaRecientesBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getRecentsList()
    }
    private fun getRecentsList() {
        viewModelDetail.getRecentsPokemon().observe(viewLifecycleOwner) {
            losPokemonesRecientes = arrayListOf()
            it.forEach {
                var poke = Pokemon(
                    it.idApi,
                    it.nombre,
                    "",
                    arrayListOf()
                )
                losPokemonesRecientes.add(poke)
            }
            if (losPokemonesRecientes.size == 0) {
                binding.noHayPokemon.visibility = View.VISIBLE
            } else {
                binding.noHayPokemon.visibility = View.GONE
            }
            adapter.losPokemones = losPokemonesRecientes
            binding.listRecyclerView.adapter = adapter
            binding.progressBar.visibility = View.GONE
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}