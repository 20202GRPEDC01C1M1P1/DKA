package br.pro.aguiar.dka.dao

import androidx.room.*
import br.pro.aguiar.dka.model.Livro

@Dao
interface LivroDAO {
    @Query("SELECT * FROM livro")
    fun all(): Array<Livro>
    @Query("SELECT * FROM livro WHERE id = :identificador")
    fun show(identificador: Int): Array<Livro>
    @Insert fun store(livro: Livro)
    @Update fun update(livro: Livro)
    @Delete fun delete(livro: Livro)
}