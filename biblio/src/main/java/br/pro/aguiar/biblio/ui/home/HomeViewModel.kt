package br.pro.aguiar.biblio.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.pro.aguiar.biblio.api.ApiClient
import br.pro.aguiar.biblio.model.Livro
import kotlinx.coroutines.launch
import kotlin.Exception

class HomeViewModel : ViewModel() {

    private val _livros = MutableLiveData<List<Livro>>()
    val livros: LiveData<List<Livro>>
        get() = _livros

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean>
        get() = _status

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String>
        get() = _msg

    init {
        _status.value = false
        _msg.value = null
        viewModelScope.launch {
            try {
                _livros.value = ApiClient.getLivroService().all()
            } catch (e: Exception) {
                _msg.value = e.message
            }
            _status.value = true
        }
    }
}