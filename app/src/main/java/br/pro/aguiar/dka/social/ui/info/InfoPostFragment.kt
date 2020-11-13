package br.pro.aguiar.dka.social.ui.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.pro.aguiar.dka.R
import br.pro.aguiar.dka.model.User
import br.pro.aguiar.dka.social.model.Comentario
import br.pro.aguiar.dka.social.model.Post
import br.pro.aguiar.dka.social.viewmodel.SocialViewModel
import br.pro.aguiar.dka.social.viewmodel.SocialViewModelFactory
import kotlinx.android.synthetic.main.fragment_info_post.*

class InfoPostFragment : Fragment() {

    private lateinit var socialViewModel: SocialViewModel
    private lateinit var infoPostViewModel: InfoPostViewModel
    private lateinit var socialViewModelFactory: SocialViewModelFactory
    private lateinit var infoPostViewModelFactory: InfoPostViewModelFactory
    private var post: Post? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_post, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let {
            socialViewModelFactory = SocialViewModelFactory()
            socialViewModel = ViewModelProvider(it, socialViewModelFactory)
                .get(SocialViewModel::class.java)
            post = socialViewModel.post
        }

        infoPostViewModelFactory = InfoPostViewModelFactory()
        infoPostViewModel = ViewModelProvider(this, infoPostViewModelFactory)
            .get(InfoPostViewModel::class.java)

        if (post != null && post!!.id != null){
            infoPostViewModel.get(post!!.id!!)
                .addSnapshotListener { snapshot, error ->
                    if (error != null){
                        // emitir um erro
                    } else if (snapshot != null && snapshot.exists()) {
                        var post = snapshot.toObject(Post::class.java)
                        if (post != null) {
                            exibirDadosNoLayut(post!!)
                            addSnapshotListenerAutor(post!!.autor!!)
                        }
                    }
                }
            infoPostViewModel.getComentarios(post!!)
                .addSnapshotListener { snapshot, error ->
                    if (error != null){
                        // emitir mensagem de erro
                    } else if (snapshot != null){
                        var comentarios = snapshot.toObjects(Comentario::class.java)
                        if (!comentarios.isNullOrEmpty()){
                            listViewComentarios.adapter =
                                ArrayAdapter(
                                    requireContext(),
                                    android.R.layout.simple_list_item_1,
                                    comentarios
                                )
                        }
                    }
                }

        } else
            findNavController().popBackStack()

        btnCurtirPost.setOnClickListener {
            infoPostViewModel.curtir(post!!)
        }

        btnComentarPost.setOnClickListener {
            findNavController().navigate(R.id.comentarioPostFragment)
        }
    }

    private fun addSnapshotListenerAutor(uid: String) {
        infoPostViewModel.getAutor(uid)
            .addSnapshotListener { value, error ->
                if (error == null && value != null) {
                    var user = value.toObject(User::class.java)
                    textVIewInfoPostAutor.text = user?.name
                }
            }
    }

    private fun exibirDadosNoLayut(post: Post) {
        textVIewInfoPostTitulo.text = post?.titulo
        textViewInfoPostConteudo.text = post?.conteudo
        textVIewInfoPostQuantidadeCurtida.text = post?.curtidas.toString()
    }
}