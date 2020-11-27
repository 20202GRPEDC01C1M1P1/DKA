package br.pro.aguiar.biblio.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.pro.aguiar.biblio.api.ApiClient
import br.pro.aguiar.biblio.model.Livro
import kotlinx.coroutines.launch

class NotificationsViewModel : ViewModel() {

    private val _status = MutableLiveData<Boolean>() // F - não retornou, T - retornou
    val status: LiveData<Boolean>
        get() = _status

    var error = false

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String>
        get() = _msg

    init {
        _status.value = false
        _msg.value = null
    }

    // status   |   error
    //    F     |     x
    //    V     |     F     - Persistiu o livro
    //    V     |     V     - Falha na persistencia

    fun store(
        titulo: String,
        paginas: String,
        ano: String,
        edicao: String,
        isbn: String,
        resumo: String,
        id: Int? = null
    ) {
        _status.value = false
        _msg.value = "Executando a persistência do Livro..."
        val livro = Livro(titulo, resumo,
            paginas.toInt(), ano.toInt(),
            edicao.toInt(), isbn, id)
        viewModelScope.launch {
            try {
                livro.store()
                error = false
                _msg.value = "Livro persistido com sucesso."
            } catch (e: Exception) {
                error = true
                _msg.value = "Livro não persistido: ${e.message}"
            }
            _status.value = true
        }
    }
}