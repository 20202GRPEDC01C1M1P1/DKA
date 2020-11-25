package br.pro.aguiar.biblio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _livro = MutableLiveData<Int>()
    val livro: LiveData<Int>
        get() = _livro

    init {
        _livro.value = null
    }

    fun selecionarLivro(id: Int){
        _livro.value = id
    }
}