package br.pro.aguiar.dka.social.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.pro.aguiar.dka.R
import br.pro.aguiar.dka.model.User
import br.pro.aguiar.dka.social.adapter.recycler.PostRecyclerAdapter
import br.pro.aguiar.dka.social.model.Post
import br.pro.aguiar.dka.social.viewmodel.SocialViewModel
import br.pro.aguiar.dka.social.viewmodel.SocialViewModelFactory
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.dashboar_fragment.*

class DashboardFragment : Fragment() {

    private var firebaseUser: FirebaseUser? = null
    private lateinit var viewModel: DashboardViewModel
    private lateinit var socialViewModel: SocialViewModel
    private lateinit var socialViewModelFactory: SocialViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dashboar_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let {
            socialViewModelFactory = SocialViewModelFactory()
            socialViewModel = ViewModelProvider(it, socialViewModelFactory)
                .get(SocialViewModel::class.java)
        }

        fabAddPost.setOnClickListener {
            socialViewModel.post = null
            findNavController().navigate(R.id.createPostFragment)
        }

        viewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        viewModel.all()
            .addOnSuccessListener {
                val posts = it.toObjects(Post::class.java)
                if (!posts.isNullOrEmpty()){
                    var postRecyclerAdapter = PostRecyclerAdapter(posts)
                    var itemTouchHelperDelete = ItemTouchHelper(
                        object : ItemTouchHelper.SimpleCallback(
                            0, ItemTouchHelper.LEFT
                        ){
                            override fun onMove(
                                recyclerView: RecyclerView,
                                viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder
                            ): Boolean = false

                            override fun onSwiped(
                                viewHolder: RecyclerView.ViewHolder,
                                direction: Int
                            ) {
                                val position = viewHolder.adapterPosition
                                val post = posts[position]
                                socialViewModel.post = post
                                findNavController().navigate(R.id.infoPostFragment)
                            }
                        }
                    )
                    itemTouchHelperDelete.attachToRecyclerView(recyclerPosts)

                    var itemTouchHelperShow = ItemTouchHelper(
                        object : ItemTouchHelper.SimpleCallback(
                            0, ItemTouchHelper.RIGHT
                        ){
                            override fun onMove(
                                recyclerView: RecyclerView,
                                viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder
                            ): Boolean = false

                            override fun onSwiped(
                                viewHolder: RecyclerView.ViewHolder,
                                direction: Int
                            ) {
                                val position = viewHolder.adapterPosition
                                val post = posts[position]
                                socialViewModel.post = post
                                findNavController().navigate(R.id.createPostFragment)
                            }
                        }
                    )
                    itemTouchHelperShow.attachToRecyclerView(recyclerPosts)

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

        var firebaseAuth = FirebaseAuth.getInstance()

        firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            viewModel.getUserInfo(firebaseUser!!.uid)
                .addOnSuccessListener {
                    var user = it.toObject(User::class.java)
                    if (user != null)
                        showSnackbar("Olá ${user?.name}")
                    else
                        showSnackbar("Olá, seja bem vindo(a).")
                }
                .addOnFailureListener {
                    showSnackbar(it.message.toString())
                }
        } else {
            activity?.finish()
        }

        btnBuscarPost.setOnClickListener {
            var termoBusca = editTextTermoBusca.text.toString()
            if (termoBusca.length > 3){
                viewModel.selectByTitle(termoBusca)
                    .addOnSuccessListener {
                        val posts = it.toObjects(Post::class.java)
                        if (!posts.isNullOrEmpty()){
                            var postRecyclerAdapter = PostRecyclerAdapter(posts)
                            var itemTouchHelperDelete = ItemTouchHelper(
                                object : ItemTouchHelper.SimpleCallback(
                                    0, ItemTouchHelper.LEFT
                                ){
                                    override fun onMove(
                                        recyclerView: RecyclerView,
                                        viewHolder: RecyclerView.ViewHolder,
                                        target: RecyclerView.ViewHolder
                                    ): Boolean = false

                                    override fun onSwiped(
                                        viewHolder: RecyclerView.ViewHolder,
                                        direction: Int
                                    ) {
                                        val position = viewHolder.adapterPosition
                                        val post = posts[position]
                                        socialViewModel.post = post
                                        findNavController().navigate(R.id.infoPostFragment)
                                    }
                                }
                            )
                            itemTouchHelperDelete.attachToRecyclerView(recyclerPosts)

                            var itemTouchHelperShow = ItemTouchHelper(
                                object : ItemTouchHelper.SimpleCallback(
                                    0, ItemTouchHelper.RIGHT
                                ){
                                    override fun onMove(
                                        recyclerView: RecyclerView,
                                        viewHolder: RecyclerView.ViewHolder,
                                        target: RecyclerView.ViewHolder
                                    ): Boolean = false

                                    override fun onSwiped(
                                        viewHolder: RecyclerView.ViewHolder,
                                        direction: Int
                                    ) {
                                        val position = viewHolder.adapterPosition
                                        val post = posts[position]
                                        socialViewModel.post = post
                                        findNavController().navigate(R.id.createPostFragment)
                                    }
                                }
                            )
                            itemTouchHelperShow.attachToRecyclerView(recyclerPosts)

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
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun showSnackbar(msg: String) {
        Snackbar.make(
            dashboard_root_element,
            msg,
            Snackbar.LENGTH_LONG
        ).show()
    }

}