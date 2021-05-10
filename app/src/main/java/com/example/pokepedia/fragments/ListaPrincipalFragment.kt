package com.example.pokepedia.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.pokepedia.adapters.AdaptadorPrincipal
import com.example.pokepedia.databinding.FragmentListaPrincipalBinding
import com.example.pokepedia.viewmodels.PokemonViewModel

/**
 * A fragment representing a list of Items.
 */
class ListaPrincipalFragment : Fragment() {

    private val viewModel: PokemonViewModel by viewModels()
    private  val adapter = AdaptadorPrincipal()

    private var _binding: FragmentListaPrincipalBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.requestPokemon()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaPrincipalBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.listRecyclerView.adapter = adapter

        viewModel.getPokemonList().observe(viewLifecycleOwner) {
            adapter.losPokemones = it
        }

        return view
    }
}