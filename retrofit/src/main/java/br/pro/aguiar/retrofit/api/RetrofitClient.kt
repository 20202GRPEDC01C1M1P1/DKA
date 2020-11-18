package br.pro.aguiar.retrofit.api

import br.pro.aguiar.retrofit.api.service.CardsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val retrofit: Retrofit = Retrofit
        .Builder()
        .baseUrl("https://docs.magicthegathering.io")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getCardsService(): CardsService {
        return retrofit.create(CardsService::class.java)
    }

}