package br.pro.aguiar.biblio.api

import br.pro.aguiar.biblio.api.service.LivroService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val retrofit = Retrofit
        .Builder()
        .baseUrl("http://biblio.aguiar.pro.br")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getLivroService() : LivroService {
        return retrofit.create(LivroService::class.java)
    }

}