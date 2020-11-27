package br.pro.aguiar.biblio.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.pro.aguiar.biblio.MainViewModel
import br.pro.aguiar.biblio.R
import br.pro.aguiar.biblio.model.Livro
import br.pro.aguiar.biblio.model.LivroSelecionado
import com.google.android.material.snackbar.Snackbar
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
                btnDashboardEditar.isEnabled = it != null
            }

        dashboardViewModel
            .livro  // Objeto livro
            .observe(viewLifecycleOwner) {
                updateUI(it)
                LivroSelecionado.livro = it
            }

        dashboardViewModel
            .msg
            .observe(viewLifecycleOwner) {
                if (!it.isNullOrBlank())
                    Snackbar.make(
                        root_dashboard_layout,
                        it,
                        Snackbar.LENGTH_LONG
                    )
                    .show()
            }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnDashboardEditar.setOnClickListener {
            findNavController().navigate(R.id.navigation_notifications)
        }
        btnDashboardExcluir.setOnClickListener {
            dashboardViewModel.excluirLivro(LivroSelecionado.livro!!)
        }
    }

    private fun updateUI(livro: Livro) {
        textViewShowLivroTitulo.text = livro.titulo
        textViewShowLivroEdicao.text = livro.edicao.toString()
        textViewShowLivroAno.text = livro.ano.toString()
        textViewShowLivroISBN.text = livro.isbn
        textViewShowLivroResumo.text = livro.resumo
    }
}