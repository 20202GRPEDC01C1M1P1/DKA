package br.pro.aguiar.retrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.pro.aguiar.retrofit.R
import br.pro.aguiar.retrofit.model.Card
import kotlinx.android.synthetic.main.list_card_recycler_adapter.view.*
import java.util.zip.Inflater

class ListCardRecylcerAdapter (
    private val cards: List<Card>
): RecyclerView.Adapter<
        ListCardRecylcerAdapter
        .ListCardViewHolder>() {

    class ListCardViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView){
        var name = itemView.textViewItemListCardName
        var toughness = itemView.textViewItemListCardToughness
        var power = itemView.textViewItemListCardPower
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCardViewHolder {
        var view = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.list_card_recycler_adapter,
                parent,
                false
            )

        var listCardViewHolder = ListCardViewHolder(view)
        return listCardViewHolder
    }

    override fun onBindViewHolder(holder: ListCardViewHolder, position: Int) {
        var card = cards.get(position)
        holder.name.text = card.name
        holder.power.text = card.power
        holder.toughness.text = card.toughness
    }

    override fun getItemCount(): Int = cards.size
}