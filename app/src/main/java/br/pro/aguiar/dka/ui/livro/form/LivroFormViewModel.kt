package br.pro.aguiar.dka.ui.livro.form

import androidx.lifecycle.ViewModel
import br.pro.aguiar.dka.dao.LivroDAO
import br.pro.aguiar.dka.model.Livro
import br.pro.aguiar.dka.room.RoomDatabase

class LivroFormViewModel : ViewModel() {
    fun store(
        livroDAO: LivroDAO,
        livroTitulo: String,
        livroAno: Int,
        livroEditoras: String,
        livroAutores: String) {
        var livro = Livro(livroTitulo, livroAno, livroEditoras, livroAutores)
        livroDAO.store(livro)
    }

}