package br.pro.aguiar.retrofit.api.service

import br.pro.aguiar.retrofit.model.Cards
import retrofit2.Call
import retrofit2.http.GET

interface CardsService {

    @GET("v1/cards")
    suspend fun all(): Cards
}