package br.pro.aguiar.dka.social.ui.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import br.pro.aguiar.dka.R
import br.pro.aguiar.dka.social.model.Post
import br.pro.aguiar.dka.social.viewmodel.SocialViewModel
import br.pro.aguiar.dka.social.viewmodel.SocialViewModelFactory
import kotlinx.android.synthetic.main.fragment_info_post.*

class InfoPostFragment : Fragment() {

    private lateinit var socialViewModel: SocialViewModel
    private lateinit var socialViewModelFactory: SocialViewModelFactory
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

            textVIewInfoPostTitulo.text = post?.titulo
            textVIewInfoPostAutor.text = post?.autor
            textViewInfoPostConteudo.text = post?.conteudo
            textVIewInfoPostQuantidadeCurtida.text = post?.curtidas.toString()
        }

    }
}