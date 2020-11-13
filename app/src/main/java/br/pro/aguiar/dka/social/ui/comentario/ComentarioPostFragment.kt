package br.pro.aguiar.dka.social.ui.comentario

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import br.pro.aguiar.dka.R
import br.pro.aguiar.dka.social.model.Post
import br.pro.aguiar.dka.social.viewmodel.SocialViewModel
import br.pro.aguiar.dka.social.viewmodel.SocialViewModelFactory
import kotlinx.android.synthetic.main.comentario_post_fragment.*

class ComentarioPostFragment : Fragment() {

    private lateinit var viewModel: ComentarioPostViewModel
    private lateinit var socialViewModelFactory: SocialViewModelFactory
    private lateinit var socialViewModel: SocialViewModel
    private var post: Post? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.comentario_post_fragment,
            container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let {
            socialViewModelFactory = SocialViewModelFactory()
            socialViewModel = ViewModelProvider(it, socialViewModelFactory)
                .get(SocialViewModel::class.java)
            post = socialViewModel.post
        }

        viewModel = ViewModelProviders
                        .of(this)
                        .get(ComentarioPostViewModel::class.java)

        viewModel.let {
            it.nomeUsuario
                .observe(viewLifecycleOwner) {
                editTextComentarioAutor.setText(it)
            }

            it.persistencia
                .observe(viewLifecycleOwner) {
                if (it)
                    findNavController().popBackStack()
            }

            it.mensagem
                .observe(viewLifecycleOwner) {
                    Toast.makeText(
                        requireContext(),
                        it,
                        Toast.LENGTH_LONG
                    ).show()
                }
        }



        btnComentarioSalvar.setOnClickListener {
            var autor = editTextComentarioAutor.text.toString()
            var conteudo = editTextComentarioConteudo.text.toString()

            if (post != null && post!!.id != null){
                viewModel.store(
                    conteudo, post!!.id!!)
            }
        }
    }

}