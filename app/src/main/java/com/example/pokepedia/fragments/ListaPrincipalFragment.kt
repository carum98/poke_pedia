package com.example.pokepedia.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.pokepedia.adapters.AdaptadorPrincipal
import com.example.pokepedia.databinding.FragmentListaPrincipalBinding
import com.example.pokepedia.viewmodels.PokemonViewModel
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.view.focusChanges
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

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
        obtenerListaPrincipal()
        binding.searchButton.isEnabled=false
        return view
    }

    private fun obtenerListaPrincipal() {
        viewModel.getPokemonList().observe(viewLifecycleOwner) {
            adapter.losPokemones = it
            binding.listRecyclerView.adapter = adapter
            binding.progressBar.visibility = View.GONE
        }
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
                    if(it.isEmpty()){
                        binding.searchButton.isEnabled=false
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
                    var elTextoDeBusqueda =binding.txtBusqueda.text.toString().toLowerCase()
                    if(!elTextoDeBusqueda.isEmpty()){
                        viewModel.getPokemon(elTextoDeBusqueda)
                        viewModel.getPokemonList().observe(viewLifecycleOwner) {
                            MostrarResultado(it.isEmpty())
                            binding.progressBar.visibility = View.GONE
                        }
                    } else {
                        binding.textInputLayout.error ="Campo requerido"
                        binding.progressBar.visibility = View.GONE
                    }
                }
        )

        binding.txtBusqueda.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            var elTextoDeBusqueda =binding.txtBusqueda.text.toString().toLowerCase()
            if(!elTextoDeBusqueda.isEmpty()){
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchPokemon(binding.txtBusqueda.text.toString())
                    return@OnEditorActionListener true
                }
            }
            false
        })

        disposable.add(
            binding.txtBusqueda.focusChanges().subscribe{
                hideKeyboard()
            }
        )
        disposable.add(
            binding.list.clicks().subscribe{
                hideKeyboard()
                binding.txtBusqueda.clearFocus()
            }
        )
    }
    fun hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
    private fun searchPokemon(text: String) {
        viewModel.getPokemon(text)
        viewModel.getPokemonList().observe(viewLifecycleOwner) {
            MostrarResultado (it.isEmpty())
        }
    }

    private fun MostrarResultado(seDebeMostrar:Boolean) {
        if(!seDebeMostrar){
            binding.busquedaFallida.visibility = View.GONE
            binding.listRecyclerView.visibility =View.VISIBLE
        }else{
            binding.busquedaFallida.visibility = View.VISIBLE
            binding.listRecyclerView.visibility =View.GONE
        }
    }

}