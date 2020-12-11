package br.pro.aguiar.marvelheros.api

import br.pro.aguiar.marvelheros.api.service.MarvelService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MarvelClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://gateway.marvel.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getMarvelService() : MarvelService {
        return retrofit.create(MarvelService::class.java)
    }
}