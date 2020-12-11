package br.pro.aguiar.marvelheros.ui.hero.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.pro.aguiar.marvelheros.api.MarvelClient
import br.pro.aguiar.marvelheros.model.MarvelCharacters
import br.pro.aguiar.marvelheros.model.MarvelData
import kotlinx.coroutines.launch

class ListHerosViewModel() : ViewModel() {

    private var _offset = 0
    private var limit = 20

    private val _heros = MutableLiveData<List<MarvelCharacters>>()
    private val _msg =  MutableLiveData<String>()
    private val _totalHeros = MutableLiveData<Int>()
    private val _countHeros = MutableLiveData<Int>()

    val heros: LiveData<List<MarvelCharacters>> = _heros
    val msg: LiveData<String> = _msg
    val totalHeros: LiveData<Int> = _totalHeros
    val countHeros: LiveData<Int> = _countHeros

    init {
        _totalHeros.value = 0
        _countHeros.value = 0
        getHerosFromApi()
    }

    private fun getHerosFromApi(offset: Int = _offset) {
        viewModelScope.launch {
            try {
                val marvelResponse = MarvelClient.getMarvelService().all(offset)
                if (marvelResponse.code == 200 && marvelResponse.data != null) {
                    _offset = marvelResponse.data.offset!!
                    _totalHeros.value = marvelResponse.data.total
                    _countHeros.value = marvelResponse.data.count
                    _heros.value = marvelResponse.data.results
                } else
                    _msg.value = "${marvelResponse.code}: ${marvelResponse.status}"
            } catch (e: Exception) {
                _msg.value = e.message
            }
        }
    }

    fun getHerosPre() {
        if (_offset > 0) getHerosFromApi(_offset-limit)
    }

    fun getHerosPro() {
        if (_offset < _totalHeros.value!!) getHerosFromApi(_offset+limit)
    }
}