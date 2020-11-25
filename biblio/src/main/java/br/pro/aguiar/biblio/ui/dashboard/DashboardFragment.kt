package br.pro.aguiar.biblio.ui.dashboard

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
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        mainViewModel =
            ViewModelProvider(requireActivity())
                .get(MainViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        mainViewModel
            .livro  // ID do livro selecionado
            .observe(viewLifecycleOwner) {
                dashboardViewModel.consultarLivro(it)
            }

        dashboardViewModel
            .livro  // Objeto livro
            .observe(viewLifecycleOwner) {
                updateUI(it)
            }

        return root
    }

    private fun updateUI(livro: Livro) {
        textViewShowLivroTitulo.text = livro.titulo
        textViewShowLivroEdicao.text = livro.edicao.toString()
        textViewShowLivroAno.text = livro.ano.toString()
        textViewShowLivroISBN.text = livro.isbn
        textViewShowLivroResumo.text = livro.resumo
    }
}