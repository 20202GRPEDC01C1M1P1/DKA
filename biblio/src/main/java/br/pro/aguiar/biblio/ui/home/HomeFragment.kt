package br.pro.aguiar.biblio.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.pro.aguiar.biblio.MainViewModel
import br.pro.aguiar.biblio.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        mainViewModel =
            ViewModelProvider(requireActivity())
                .get(MainViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        homeViewModel
            .livros
            .observe(viewLifecycleOwner) {
                listViewListaLivros
                    .adapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_list_item_1,
                        it
                    )
            }

        homeViewModel
            .msg
            .observe(viewLifecycleOwner) {
                if (!it.isNullOrBlank()) showSnackbar(it)
            }

        homeViewModel
            .status
            .observe(viewLifecycleOwner) {
                if (it)
                    progressBarHome.visibility = View.GONE
                else
                    progressBarHome.visibility = View.VISIBLE
            }
        return root
    }

    private fun showSnackbar(msg: String) {
        Snackbar.make(
            root_layout_home,
            msg,
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listViewListaLivros.setOnItemClickListener { adapterView, view, i, l ->
            val livro = homeViewModel.livros.value!!.get(i)
            mainViewModel.selecionarLivro(livro.id!!)
            findNavController().navigate(R.id.navigation_dashboard)
        }
    }
}