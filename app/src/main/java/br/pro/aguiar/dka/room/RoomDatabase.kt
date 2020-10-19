package br.pro.aguiar.dka.room

import androidx.room.Database
import androidx.room.RoomDatabase
import br.pro.aguiar.dka.dao.LivroDAO
import br.pro.aguiar.dka.model.Livro

@Database(
    entities = arrayOf(
            Livro::class
        ),
    version = 1
)
abstract class RoomDatabase: RoomDatabase() {
    abstract fun livroDAO(): LivroDAO
}