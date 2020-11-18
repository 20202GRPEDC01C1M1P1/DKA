package br.pro.aguiar.retrofit.ui.card.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.pro.aguiar.retrofit.api.RetrofitClient
import br.pro.aguiar.retrofit.model.Card
import br.pro.aguiar.retrofit.model.Cards
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListCardViewModel : ViewModel() {
    var listCard = MutableLiveData<List<Card>>()
    var msg = MutableLiveData<String>()

    init {
        var call = RetrofitClient.getCardsService().all()
        call.enqueue(
            object : Callback<Cards> {
                override fun onResponse(call: Call<Cards>, response: Response<Cards>) {
                    var cards = response.body()
                    listCard.value = cards!!.cards
                }
                override fun onFailure(call: Call<Cards>, t: Throwable) {
                    msg.value = t.message
                }
            }
        )
    }

}