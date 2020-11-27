package br.pro.aguiar.biblio.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.pro.aguiar.biblio.MainViewModel
import br.pro.aguiar.biblio.R
import br.pro.aguiar.biblio.model.Livro
import br.pro.aguiar.biblio.model.LivroSelecionado
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_notifications.*

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_notifications, container, false)

        notificationsViewModel.let {
            it.status
                .observe(viewLifecycleOwner) { status ->
                    if (status) {
                        if (!it.error) {
                            clearUI()
                        }
                    }
                }
            it.msg
                .observe(viewLifecycleOwner) {
                    if (!it.isNullOrBlank()){
                        Snackbar
                            .make(
                                root_notifications_layout,
                                it,
                                Snackbar.LENGTH_LONG
                            )
                            .show()
                    }
                }
        }


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (LivroSelecionado.livro != null) {
            val livro = LivroSelecionado.livro
            updateUI(livro)
            LivroSelecionado.livro = null
        }
        fabCreateLivroSalvar.setOnClickListener {
            // Pegar as informacoes dos campos
            val titulo = editTextCreateLivroTitulo.text.toString()
            val paginas = editTextCreateLivroPaginas.text.toString()
            val ano = editTextCreateLivroAno.text.toString()
            val edicao = editTextCreateLivroEdicao.text.toString()
            val isbn = editTextCreateLivroISBN.text.toString()
            val resumo = editTextCreateLivroResumo.text.toString()
            // passar para a ViewModel persistir
            notificationsViewModel.store(
                titulo, paginas, ano, edicao, isbn, resumo,
                LivroSelecionado.livro?.id
            )
        }
    }

    private fun updateUI(livro: Livro?) {
        editTextCreateLivroTitulo.setText(livro?.titulo)
        editTextCreateLivroPaginas.setText(livro?.paginas.toString())
        editTextCreateLivroAno.setText(livro?.ano.toString())
        editTextCreateLivroEdicao.setText(livro?.edicao.toString())
        editTextCreateLivroISBN.setText(livro?.isbn)
        editTextCreateLivroResumo.setText(livro?.resumo)
    }

    private fun clearUI() {
        editTextCreateLivroTitulo.setText("")
        editTextCreateLivroPaginas.setText("")
        editTextCreateLivroAno.setText("")
        editTextCreateLivroEdicao.setText("")
        editTextCreateLivroISBN.setText("")
        editTextCreateLivroResumo.setText("")
    }
}