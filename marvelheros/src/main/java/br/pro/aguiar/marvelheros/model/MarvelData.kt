package br.pro.aguiar.marvelheros.model

class MarvelData (
    val offset: Int? = null,
    val limit: Int? = null,
    val total: Int? = null,
    val count: Int? = null,
    val results: List<MarvelCharacters>? = null
)
