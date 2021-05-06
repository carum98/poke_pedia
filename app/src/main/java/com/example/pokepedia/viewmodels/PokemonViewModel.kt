package com.example.pokepedia.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokepedia.BuildConfig
import com.example.pokepedia.modelos.Pokemon
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

    fun ListeLosPokemon(offset: String) {
        service.getPokemonList("100",offset)
            .enqueue(object : Callback<PokemonResponse> {
                override fun onResponse(
                    call: Call<PokemonResponse>,
                    response: Response<PokemonResponse>
                ) {
                    response.body()?.let {
                        // TODO: Cuando el request se completa, notificamos a los suscriptores
                        pokemonList.postValue(it.pokemos)
                    }
                }

                override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                    val a = ""
                }

            })
    }

    fun getPokemonList(): LiveData<List<Pokemon>> {
        return pokemonList
    }
}