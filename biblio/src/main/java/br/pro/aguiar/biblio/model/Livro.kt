package br.pro.aguiar.biblio.model

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
}