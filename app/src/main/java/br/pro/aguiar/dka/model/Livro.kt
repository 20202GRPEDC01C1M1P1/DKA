package br.pro.aguiar.dka.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Livro (
    var titulo: String,
    var ano: Int,
    var editora: String,
    var autores: String,
    @PrimaryKey var id: Int? = null
) {
    override fun toString(): String {
        return "$titulo"
    }
}