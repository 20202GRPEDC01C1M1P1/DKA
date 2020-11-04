package br.pro.aguiar.dka.social.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.pro.aguiar.dka.R
import br.pro.aguiar.dka.social.PostRecyclerAdapter
import br.pro.aguiar.dka.social.model.Post
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.dashboar_fragment.*

class DashboardFragment : Fragment() {

    private lateinit var viewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dashboar_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        
        fabAddPost.setOnClickListener {
            findNavController().navigate(R.id.createPostFragment)
        }

        viewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        viewModel.all()
            .addOnSuccessListener {
                val posts = it.toObjects(Post::class.java)
                if (!posts.isNullOrEmpty()){
                    var postRecyclerAdapter = PostRecyclerAdapter(posts)
                    recyclerPosts.adapter = postRecyclerAdapter
                    recyclerPosts.layoutManager = LinearLayoutManager(requireContext())
                } else {
                    showSnackbar("Nenhum Post encontrado.")
                }
            }
            .addOnFailureListener {
                showSnackbar(it.message.toString())
            }
            .addOnCompleteListener {
                // esconder o progressbar
            }
    }

    private fun showSnackbar(msg: String) {
        Snackbar.make(
            dashboard_root_element,
            msg,
            Snackbar.LENGTH_LONG
        ).show()
    }

}