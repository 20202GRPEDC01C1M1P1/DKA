package br.pro.aguiar.marvelheros.ui.hero.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.pro.aguiar.marvelheros.api.MarvelClient
import br.pro.aguiar.marvelheros.model.MarvelCharacters
import br.pro.aguiar.marvelheros.repository.RepositoryService
import kotlinx.coroutines.launch

class ListHerosViewModel() : ViewModel() {
    private val _heros = MutableLiveData<List<MarvelCharacters>>()
    private val _msg =  MutableLiveData<String>()

    val heros: LiveData<List<MarvelCharacters>> = _heros
    val msg: LiveData<String> = _msg

    init {
        viewModelScope.launch {
            try {
                val marvelResponse = MarvelClient.getMarvelService().all()
                if(marvelResponse.code == 200 && marvelResponse.data != null)
                    _heros.value = marvelResponse.data.results
                else
                    _msg.value = "${marvelResponse.code}: ${marvelResponse.status}"
            } catch (e: Exception) {
                _msg.value = e.message
            }
        }
    }
}