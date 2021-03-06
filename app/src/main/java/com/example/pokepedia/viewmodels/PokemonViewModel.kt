package com.example.pokepedia.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokepedia.BuildConfig
import com.example.pokepedia.modelos.Pokemon
import com.example.pokepedia.modelos.PokemonChain
import com.example.pokepedia.modelos.PokemonDetail
import com.example.pokepedia.modelos.PokemonResponse
import com.example.pokepedia.service.Service
import com.google.gson.JsonArray
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonViewModel: ViewModel() {
    private val pokemonList = MutableLiveData<List<Pokemon>>()
    private val pokemon = MutableLiveData<Pokemon>()
    private val pokemonDetail = MutableLiveData<PokemonDetail>()
    private var pokemonChain = MutableLiveData<PokemonChain>()
    private var service: Service

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(Service::class.java)
    }

    fun requestPokemon() {
        service.getPokemonList("200","0")
            .enqueue(object : Callback<PokemonResponse> {
                override fun onResponse(call: Call<PokemonResponse>, response: Response<PokemonResponse>) {
                    response.body()?.let {
                        pokemonList.postValue(it.pokemos.onEach {
                                elPokemon->
                            elPokemon.id= elPokemon.url!!.split("/")[6]
                        })
                       // pokemonList.postValue(it.pokemos)
                    }
                }
                override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                    val a = ""
                }
            })
    }

    fun getPokemon(elPokemon:String){
        var elLlamado =   service.getPokemon(elPokemon)
        elLlamado.enqueue(object : Callback<Pokemon> {
            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
            }
            override fun onResponse(
                call: Call<Pokemon>,
                response: Response<Pokemon>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                         var  elPokemonEncontrado= it
                        /*  elPokemonEncontrado.urlImagen="${BuildConfig.URLIMAGENPOKEMON}${elPokemonEncontrado.id}.png"*/
                        pokemon.postValue(elPokemonEncontrado)
                        pokemonList.postValue(listOf(elPokemonEncontrado))
                    }
                }else{
                    Log.d("FAllo","Uno")
                    pokemonList.postValue(listOf())
                }
            }
        })
    }

    fun getPokemonList(): LiveData<List<Pokemon>> {
        return pokemonList
    }

    fun getPokemonDetail(elPokemon:String){
        service.getPokemonDetail(elPokemon)
            .enqueue(object : Callback<PokemonDetail>{
                override fun onResponse(call: Call<PokemonDetail>, response: Response<PokemonDetail>) {
                    response.body()?.let {
                        pokemonDetail.postValue(it)
                    }
                }
                override fun onFailure(call: Call<PokemonDetail>, t: Throwable) {
                    val a = ""
                }
            })
    }
    fun getPokemonEvolution(elPokemon:String){
        service.getPokemonEvolution(elPokemon)
            .enqueue(object : Callback<PokemonChain>{
                override fun onResponse(call: Call<PokemonChain>, response: Response<PokemonChain>) {
                    Log.d("Pruebas Irvin: ",response.isSuccessful().toString())
                    if(response.isSuccessful){
                        response.body()?.let {
                            // TODO: Cuando el request se completa, notificamos a los suscriptores
                           pokemonChain.postValue(it)
                        }
                    }else{
                        pokemonChain.postValue(null)
                    }
                }
                override fun onFailure(call: Call<PokemonChain>, t: Throwable) {
                    val a = ""
                }
            })
    }
    fun getDataPokemonDetail(): LiveData<PokemonDetail> {
        return pokemonDetail
    }
    fun getPokemonEvolutionData(): LiveData<PokemonChain> {
        return pokemonChain
    }
    fun getPokemonData(): LiveData<Pokemon> {
        return pokemon
    }
}