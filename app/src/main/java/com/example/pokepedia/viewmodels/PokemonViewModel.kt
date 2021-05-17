package com.example.pokepedia.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokepedia.BuildConfig
import com.example.pokepedia.modelos.Pokemon
import com.example.pokepedia.modelos.PokemonDetail
import com.example.pokepedia.modelos.PokemonResponse
import com.example.pokepedia.service.Service
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonViewModel: ViewModel() {
    private val pokemonList = MutableLiveData<List<Pokemon>>()
    private val pokemonDetail = MutableLiveData<PokemonDetail>()
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
        service.getPokemonList("100","0")
            .enqueue(object : Callback<PokemonResponse> {
                override fun onResponse(call: Call<PokemonResponse>, response: Response<PokemonResponse>) {
                    response.body()?.let {
                        pokemonList.postValue(it.pokemos.onEach {
                                elPokemon->
                            elPokemon.id= elPokemon.url.split("/")[6]
                            elPokemon.urlImagen =    "${BuildConfig.URLIMAGENPOKEMON}${elPokemon.id}.png"
                        })
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
                val a = ""
                Log.d("FAllo","Uno")
            }
            override fun onResponse(
                call: Call<Pokemon>,
                response: Response<Pokemon>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        // TODO: Cuando el request se completa, notificamos a los suscriptores
                        pokemonList.postValue(listOf(it).onEach {
                                elPokemon->
                            elPokemon.urlImagen = "${BuildConfig.URLIMAGENPOKEMON}${elPokemon.id}.png"
                        })
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

    fun getDataPokemonDetail(): LiveData<PokemonDetail> {
        return pokemonDetail
    }
}