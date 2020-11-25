package br.pro.aguiar.retrofit.ui.card.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.pro.aguiar.retrofit.api.RetrofitClient
import br.pro.aguiar.retrofit.api.service.CardsService
import br.pro.aguiar.retrofit.model.Card
import br.pro.aguiar.retrofit.model.Cards
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListCardViewModel : ViewModel() {
    var listCard = MutableLiveData<List<Card>>()
    var msg = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            try {
                var cards = RetrofitClient.getCardsService().all()
                listCard.value = cards!!.cards
            } catch (e: Exception) {
                msg.value = e.message
            }
        }

//        call.execute()
//        call.enqueue(
//            object : Callback<Cards> {
//                override fun onResponse(call: Call<Cards>, response: Response<Cards>) {
//                    var cards = response.body()
//                    listCard.value = cards!!.cards
//                }
//                override fun onFailure(call: Call<Cards>, t: Throwable) {
//                    msg.value = t.message
//                }
//            }
//        )
    }

}