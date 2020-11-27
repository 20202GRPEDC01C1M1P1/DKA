package br.pro.aguiar.biblio.model

import br.pro.aguiar.biblio.api.ApiClient

class Livro (
    var titulo: String? = null,
    var resumo: String? = null,
    var paginas: Int? = null,
    var ano: Int? = null,
    var edicao: Int? = null,
    var isbn: String? = null,
    var id: Int? = null
) {
    override fun toString(): String {
        return "$titulo ($ano)"
    }

    suspend fun store() {
        if (id == null)
            ApiClient.getLivroService().store(this)
        else
            ApiClient.getLivroService().update(id!!, this)
    }

    suspend fun delete(): Int = ApiClient.getLivroService().delete(id!!)
}