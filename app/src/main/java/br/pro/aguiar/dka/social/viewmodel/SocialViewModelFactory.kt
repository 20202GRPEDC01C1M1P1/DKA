package br.pro.aguiar.dka.social.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SocialViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SocialViewModel::class.java)){
            return SocialViewModel() as T
        } else
            throw IllegalArgumentException(
                "SocialViewModelFactory socilita um SocialViewModel"
            )
    }
}