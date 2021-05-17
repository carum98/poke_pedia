package com.example.pokepedia.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.pokepedia.BuildConfig
import com.example.pokepedia.adapters.AdaptadorDeLosFavoritos
import com.example.pokepedia.databinding.FragmentListaFavoritosBinding
import com.example.pokepedia.modelos.Pokemon
import com.example.pokepedia.viewmodels.PokemonDetailViewModel
import com.example.pokepedia.viewmodels.PokemonViewModel
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
                    "${BuildConfig.URLIMAGENPOKEMON}${it.idApi}.png"
                )
                losPokemonesFavoritos.add(poke)
            }
            if (losPokemonesFavoritos.size == 0) {
                binding.noHayPokemon.visibility = View.VISIBLE
                binding.busquedaFallida.visibility = View.GONE
            }
            adapter.losPokemones = losPokemonesFavoritos
            binding.listRecyclerView.adapter = adapter
            /*       var bindingEmpty: EmptyViewBinding = EmptyViewBinding.inflate(LayoutInflater.from(context), binding.root)


            if(losPokemonesFavoritos.size==0){
                bindingEmpty.textoImagenEmpty.text =R.string.empty_fav.toString()
                bindingEmpty.imagenPrincipalEmpty.setImageResource(R.drawable.pokemo_no_encontrado)
            } else{
                bindingEmpty.textoImagenEmpty.text =R.string.textBusquedaFallida.toString()
                bindingEmpty.imagenPrincipalEmpty.setImageResource(R.drawable.pokemo_no_encontrado)
            }*/
            // losPokemonesFavoritos.clear()
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
                    binding.textInputLayout.error = if (it.isEmpty()) "Campo requerido" else null
                }
        )


        disposable.add(
            binding.searchButton.clicks()
                .subscribe {
                    var elTextoDeBusqueda=binding.txtBusqueda.text.toString()
                    if(elTextoDeBusqueda.isEmpty()){
                        getFavoriteList()
                    }else{
                        viewModelDetail.getFavoritePokemonByName(elTextoDeBusqueda).observe(viewLifecycleOwner) {
                            losPokemonesFavoritos= arrayListOf()
                            if(it!=null){
                                var poke=Pokemon(it.idApi,it.nombre,"","${BuildConfig.URLIMAGENPOKEMON}${it.idApi}.png")
                                losPokemonesFavoritos.add(poke)
                                if(losPokemonesFavoritos.size==0){
                                    binding.noHayPokemon.visibility=View.GONE
                                    binding.busquedaFallida.visibility=View.VISIBLE
                                }
                            }else{
                                getFavoriteList()
                            }
                            adapter.losPokemones = losPokemonesFavoritos
                            binding.listRecyclerView.adapter=adapter
                        }
                        MostrarResultado(losPokemonesFavoritos.isEmpty())
                    }


                }
        )
    }
    override fun onDestroyView() {
        super.onDestroyView()
        disposable.clear()
        _binding = null
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


