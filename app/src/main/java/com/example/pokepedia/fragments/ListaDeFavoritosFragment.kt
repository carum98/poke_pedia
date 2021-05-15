package com.example.pokepedia.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.pokepedia.BuildConfig
import com.example.pokepedia.adapters.AdaptadorDeLosFavoritos
import com.example.pokepedia.R
import com.example.pokepedia.adapters.AdaptadorPrincipal
import com.example.pokepedia.databinding.FragmentListaFavoritosBinding
import com.example.pokepedia.databinding.FragmentListaPrincipalBinding
import com.example.pokepedia.modelos.Pokemon
import com.example.pokepedia.viewmodels.PokemonDetailViewModel
import com.example.pokepedia.viewmodels.PokemonViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * A fragment representing a list of Items.
 */
class ListaDeFavoritosFragment : Fragment() {
   private val viewModel: PokemonViewModel by viewModels()
    private  val adapter = AdaptadorDeLosFavoritos()

    private var _binding: FragmentListaFavoritosBinding? = null
    private val binding get() = _binding!!
    private val disposable = CompositeDisposable()
    private lateinit var losPokemonesFavoritos: MutableList<Pokemon>

    private  val  viewModelDetail: PokemonDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {



        _binding = FragmentListaFavoritosBinding.inflate(inflater, container, false)
        val view = binding.root

       // binding.listRecyclerView.adapter = adapter


      /*  viewModel.getPokemonList().observe(viewLifecycleOwner) {
            adapter.losPokemones = it
            binding.listRecyclerView.adapter=adapter
        }*/

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelDetail.getFavoritePokemon().observe(viewLifecycleOwner) {
            losPokemonesFavoritos= arrayListOf()
            it.forEach{
                var poke=Pokemon(it.idApi,it.nombre,"","${BuildConfig.URLIMAGENPOKEMON}${it.idApi}.png")

                losPokemonesFavoritos.add(poke)
            }
            adapter.losPokemones = losPokemonesFavoritos
            binding.listRecyclerView.adapter=adapter
            // losPokemonesFavoritos.clear()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        disposable.clear()
        _binding = null
    }



}


