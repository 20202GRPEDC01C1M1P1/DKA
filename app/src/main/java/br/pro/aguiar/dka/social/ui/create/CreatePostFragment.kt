package br.pro.aguiar.dka.social.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import br.pro.aguiar.dka.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_social.*
import kotlinx.android.synthetic.main.create_post_fragment.*
import kotlinx.android.synthetic.main.dashboar_fragment.*

class CreatePostFragment : Fragment() {

    companion object {
        fun newInstance() = CreatePostFragment()
    }

    private lateinit var viewModel: CreatePostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_post_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CreatePostViewModel::class.java)

        fabPostSave.setOnClickListener {
            var titulo = editTextCreatePostTitulo.text.toString()
            var conteudo = editTextCreatePostConteudo.text.toString()
            viewModel.store(
                titulo, conteudo
            )
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        showSnackbar("Post criado com sucesso.")
                        findNavController().popBackStack()
                    } else {
                        showSnackbar(it.exception?.message.toString())
                    }
                }
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