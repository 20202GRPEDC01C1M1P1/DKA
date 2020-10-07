package br.pro.aguiar.dka.model

class User (
    var id: Int,
    var name: String
) {
    override fun toString(): String {
        return "$id: $name"
    }
}
