package com.example.pokepedia.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.pokepedia.BuildConfig
import com.example.pokepedia.adapters.AdaptadorDeLosFavoritos
import com.example.pokepedia.databinding.FragmentListaFavoritosBinding
import com.example.pokepedia.modelos.Pokemon
import com.example.pokepedia.viewmodels.PokemonDetailViewModel
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

/**
 * A fragment representing a list of Items.
 */
class ListaDeFavoritosFragment : Fragment() {
    //   private val viewModel: PokemonViewModel by viewModels()
    private val adapter = AdaptadorDeLosFavoritos()

    private var _binding: FragmentListaFavoritosBinding? = null
    private val binding get() = _binding!!
    private val disposable = CompositeDisposable()
    private lateinit var losPokemonesFavoritos: MutableList<Pokemon>

    private val viewModelDetail: PokemonDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaFavoritosBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Busqueda()
        getFavoriteList()
    }

    private fun getFavoriteList() {
        viewModelDetail.getFavoritePokemon().observe(viewLifecycleOwner) {
            losPokemonesFavoritos = arrayListOf()
            it.forEach {
                var poke = Pokemon(
                    it.idApi,
                    it.nombre,
                    "",
                    arrayListOf()
                )
                losPokemonesFavoritos.add(poke)
            }
            if (losPokemonesFavoritos.size == 0) {
                binding.noHayPokemon.visibility = View.VISIBLE
                binding.busquedaFallida.visibility = View.GONE
                binding.txtBusqueda.visibility = View.GONE
                binding.searchButton.visibility = View.GONE
            }else{
                binding.searchButton.isEnabled=false
                binding.txtBusqueda.visibility = View.VISIBLE
                binding.searchButton.visibility = View.VISIBLE
            }
            adapter.losPokemones = losPokemonesFavoritos
            binding.listRecyclerView.adapter = adapter
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun Busqueda() {
        disposable.add(
            binding.txtBusqueda.textChanges()
                .skipInitialValue()
                .debounce(300, TimeUnit.MILLISECONDS)
                .map { it.toString() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if(it.isEmpty()){
                        binding.searchButton.isEnabled=false
                        binding.textInputLayout.error ="Campo requerido"
                    }else{
                        binding.searchButton.isEnabled=true
                        binding.textInputLayout.error =null
                    }
                }
        )


        disposable.add(
            binding.searchButton.clicks()
                .subscribe {
                    binding.progressBar.visibility = View.VISIBLE
                    hideKeyboard()
                    var elTextoDeBusqueda = binding.txtBusqueda.text.toString()
                    if (elTextoDeBusqueda.isEmpty()) {
                        getFavoriteList()
                        binding.progressBar.visibility = View.GONE
                    } else {
                        searchPokemon(elTextoDeBusqueda)
                        binding.progressBar.visibility = View.GONE
                    }
                }
        )

        binding.txtBusqueda.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchPokemon(binding.txtBusqueda.text.toString())
                return@OnEditorActionListener true
            }
            false
        })
    }
    fun hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        disposable.clear()
        _binding = null
    }

    private fun searchPokemon(text: String) {
        viewModelDetail.getFavoritePokemonByName(text.toLowerCase()).observe(viewLifecycleOwner) {
            losPokemonesFavoritos = arrayListOf()
            it.forEach {
                var poke = Pokemon(
                    it.idApi, it.nombre,
                    "",
                    arrayListOf()
                )
                losPokemonesFavoritos.add(poke)
            }
            adapter.losPokemones = losPokemonesFavoritos
            binding.listRecyclerView.adapter = adapter
            MostrarResultado(losPokemonesFavoritos.isEmpty())
        }

    }

    private fun MostrarResultado(seDebeMostrar: Boolean) {
        if (seDebeMostrar) {
            if (!binding.busquedaFallida.isVisible) {
                binding.busquedaFallida.visibility = View.VISIBLE
                binding.listRecyclerView.visibility = View.GONE
            }
        } else {
            if (!binding.listRecyclerView.isVisible) {
                binding.busquedaFallida.visibility = View.GONE
                binding.listRecyclerView.visibility = View.VISIBLE
            }
        }
    }


}


