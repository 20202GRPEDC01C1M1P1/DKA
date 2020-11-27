package br.pro.aguiar.biblio.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.pro.aguiar.biblio.api.ApiClient
import br.pro.aguiar.biblio.model.Livro
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    private val _livro = MutableLiveData<Livro>()
    val livro: LiveData<Livro>
        get() = _livro

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String>
        get() = _msg

    fun consultarLivro(id: Int) {
        viewModelScope.launch {
            try {
                _livro.value = ApiClient.getLivroService().show(id)
            } catch (e: Exception) {
                _msg.value = e.message
            }
        }
    }

    fun excluirLivro(livro: Livro) {
        _msg.value = "Livro de ID: ${livro.id}, em processo de exclusão."
        viewModelScope.launch {
            try {
                val id = livro.delete()
                _msg.value = "Livro de ID: $id, excluido com sucesso."
            } catch (e: Exception) {
                _msg.value = e.message
            }
        }
    }
}