package br.pro.aguiar.dka.social.adapter.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.pro.aguiar.dka.R
import br.pro.aguiar.dka.social.model.Post
import kotlinx.android.synthetic.main.adapter_post_recycler.view.*

class PostRecyclerAdapter (
    private var posts: List<Post>,
    private var actionClick: (Post) -> Unit
): RecyclerView.Adapter<PostRecyclerAdapter.PostViewHolder>() {

    class PostViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {
        val textViewPostTitulo = itemView.textViewPostTitulo
        val textViewPostResumo = itemView.textViewPostResumo
        val textViewPostCurtidas= itemView.textViewPostCurtidas
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.adapter_post_recycler,
                parent,
                false
            )
        val viewHolder = PostViewHolder(view)

        return viewHolder
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.textViewPostTitulo.text = post.titulo
        holder.textViewPostResumo.text = post.getResumo()
        holder.textViewPostCurtidas.text = post.curtidas.toString()
        holder.itemView.setOnClickListener {
            actionClick(post)
        }
    }

    override fun getItemCount(): Int = posts.size
}