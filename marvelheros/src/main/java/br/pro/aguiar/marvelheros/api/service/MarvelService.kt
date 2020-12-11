package br.pro.aguiar.marvelheros.api.service

import br.pro.aguiar.marvelheros.model.MarvelResponse
import br.pro.aguiar.marvelheros.repository.RepositoryService
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService {

    @GET("/v1/public/characters")
    suspend fun all(@Query("offset") offiset: Int = 0,
                    //@Query("limit") limit: Int = 20,
                    @Query("ts") ts: Int = 1,
                    @Query("hash") hash: String = "cdf95a0a2b31b263acadfc08529be623",
                    @Query("apikey") apikey: String = "cf58b8d0b024f8fdd3230a4e51075e92"
                    ) : MarvelResponse

    @GET("/v1/public/characters/{characterId}")
    suspend fun show(@Path("characterId") id: Int,
                     @Query("ts") ts: Int = 1,
                     @Query("hash") hash: String = "cdf95a0a2b31b263acadfc08529be623",
                     @Query("apikey") apikey: String = "cf58b8d0b024f8fdd3230a4e51075e92"
                    ): MarvelResponse
}