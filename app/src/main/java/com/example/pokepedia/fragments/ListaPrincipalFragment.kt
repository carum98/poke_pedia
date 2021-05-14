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
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

/**
 * A fragment representing a list of Items.
 */
class ListaPrincipalFragment : Fragment() {

    private val viewModel: PokemonViewModel by viewModels()
    private  val adapter = AdaptadorPrincipal()

    private var _binding: FragmentListaPrincipalBinding? = null
    private val binding get() = _binding!!
    private val disposable = CompositeDisposable()

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
            binding.listRecyclerView.adapter=adapter
        }

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        disposable.clear()
        _binding = null
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        disposable.add(
            binding.txtBusqueda.textChanges()
                .skipInitialValue()
                .debounce(300, TimeUnit.MILLISECONDS)
                .map { it.toString() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    binding.textInputLayout.error = if (it.isEmpty()) "Campo requerido" else null
                }
        )


        disposable.add(
            binding.searchButton.clicks()
                .subscribe {
                    viewModel.getPokemon(binding.txtBusqueda.text.toString())
                    viewModel.getPokemonList().observe(viewLifecycleOwner) {
                        MostrarResultado (it.isEmpty())
                    }

                }
        )


    }
    private fun MostrarResultado(seDebeMostrar:Boolean) {
        if(!seDebeMostrar){
            binding.busquedaFallida1.visibility = View.GONE
            binding.listRecyclerView.visibility =View.VISIBLE
        }else{
            binding.busquedaFallida1.visibility = View.VISIBLE
            binding.listRecyclerView.visibility =View.GONE
        }
    }

}