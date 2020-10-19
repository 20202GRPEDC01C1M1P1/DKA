package br.pro.aguiar.dka.ui.livro.lista

import androidx.lifecycle.ViewModel
import br.pro.aguiar.dka.model.Livro
import br.pro.aguiar.dka.room.RoomDatabase

class LivroListaViewModel : ViewModel() {
    fun all(db: RoomDatabase): Array<Livro> {
        return db.livroDAO().all()
    }

}