package br.pro.aguiar.dka.social.ui.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class InfoPostViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InfoPostViewModel::class.java)){
            return InfoPostViewModel() as T
        }
        throw IllegalArgumentException(
            "InfoPostViewModelFactory socilita um InfoPostViewModel"
        )
    }
}