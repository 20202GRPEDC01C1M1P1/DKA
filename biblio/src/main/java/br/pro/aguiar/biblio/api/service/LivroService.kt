package br.pro.aguiar.biblio.api.service

import br.pro.aguiar.biblio.model.Livro
import retrofit2.http.*

interface LivroService {

    @GET("api/livros")
    suspend fun all() : List<Livro>

    @GET("api/livros/{id}")
    suspend fun show(@Path("id") id: Int) : Livro

    @POST("api/livros")
    suspend fun store(@Body livro: Livro) : Livro

    @PUT("api/livros")
    suspend fun update(@Body livro: Livro) : Livro

    @DELETE("api/livros/{id}")
    suspend fun delete(@Path("id") id: Int) : Int

}