package br.pro.aguiar.marvelheros.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.pro.aguiar.marvelheros.R
import br.pro.aguiar.marvelheros.model.MarvelCharacters
import kotlinx.android.synthetic.main.heros_recycler_layout.view.*

class HerosRecyclerAdapter (
    private val listHeros: List<MarvelCharacters>
): RecyclerView.Adapter<HerosRecyclerAdapter.HerosViewHolder>() {

    class HerosViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textViewId = itemView.textViewHerosRecyclerId
        val textViewName = itemView.textViewHerosRecyclerName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HerosViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.heros_recycler_layout,
                parent, false
            )
        val herosViewHolder = HerosViewHolder(view)
        return herosViewHolder
    }

    override fun onBindViewHolder(holder: HerosViewHolder, position: Int) {
        val hero = listHeros[position]
        holder.textViewId.text = hero.id.toString()
        holder.textViewName.text = hero.name
    }

    override fun getItemCount(): Int = listHeros.size
}