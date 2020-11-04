package br.pro.aguiar.dka.social.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import br.pro.aguiar.dka.R
import br.pro.aguiar.dka.social.viewmodel.SocialViewModel
import br.pro.aguiar.dka.social.viewmodel.SocialViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.create_post_fragment.*
import java.lang.Exception

class CreatePostFragment : Fragment() {

    private lateinit var createPostViewModel: CreatePostViewModel
    private lateinit var socialViewModel: SocialViewModel
    private lateinit var socialViewModelFactory: SocialViewModelFactory
    private var postId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_post_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            socialViewModelFactory = SocialViewModelFactory()
            socialViewModel = ViewModelProvider(it, socialViewModelFactory)
                .get(SocialViewModel::class.java)
            var post = socialViewModel.post
            if (post != null){
                postId = post.id
                editTextCreatePostTitulo.setText(post.titulo)
                editTextCreatePostConteudo.setText(post.conteudo)
                fabPostDelete.visibility = View.VISIBLE
                fabPostDelete.isEnabled = true
            }
        }
        createPostViewModel = ViewModelProviders.of(this).get(CreatePostViewModel::class.java)

        fabPostSave.setOnClickListener {
            var titulo = editTextCreatePostTitulo.text.toString()
            var conteudo = editTextCreatePostConteudo.text.toString()
            if (postId == null) {
                createPostViewModel.store(
                    titulo, conteudo)
                    .addOnCompleteListener {
                        actionCompleteListener(
                            it.isSuccessful,
                            "Post criado com sucesso.",
                            it.exception)
                    }
            } else {
                createPostViewModel.update(
                    titulo, conteudo, postId!!)
                    .addOnCompleteListener {
                        actionCompleteListener(it.isSuccessful,
                            "Post atualizado com sucesso.",
                            it.exception)
                    }
            }
        }
        fabPostDelete.setOnClickListener {
            createPostViewModel.delete(
                postId!!)
                .addOnCompleteListener {
                    actionCompleteListener(
                        it.isSuccessful,
                        "Post excluido com sucesso.",
                        it.exception
                    )
                }
        }
    }

    private fun actionCompleteListener(
        isSuccessful: Boolean,
        msg: String,
        exception: Exception?) {
        if (isSuccessful) {
            showSnackbar(msg)
            findNavController().popBackStack()
        } else {
            showSnackbar(exception?.message.toString())
        }
    }

    private fun showSnackbar(msg: String) {
        Snackbar.make(
            create_post_root_element,
            msg,
            Snackbar.LENGTH_LONG
        ).show()
    }

}