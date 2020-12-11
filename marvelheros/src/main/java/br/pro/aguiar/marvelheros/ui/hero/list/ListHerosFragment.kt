package br.pro.aguiar.marvelheros.ui.hero.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.pro.aguiar.marvelheros.R
import br.pro.aguiar.marvelheros.adapter.HerosRecyclerAdapter
import br.pro.aguiar.marvelheros.model.MarvelCharacters
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.list_heros_fragment.*

class ListHerosFragment : Fragment() {

    companion object {
        fun newInstance() = ListHerosFragment()
    }

    private lateinit var listHerosViewModel: ListHerosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_heros_fragment, container, false)
        listHerosViewModel = ViewModelProvider(this).get(ListHerosViewModel::class.java)
        listHerosViewModel.let {
            it.msg.observe(viewLifecycleOwner) { msg -> showSnackbar(msg) }
            it.heros.observe(viewLifecycleOwner) { heros -> setupRecycerView(heros) }
            it.totalHeros.observe(viewLifecycleOwner) { totalHeros ->
                textViewListHerosTotal.text = totalHeros.toString()
            }
            it.countHeros.observe(viewLifecycleOwner) { countHeros ->
                textViewListHerosCount.text = countHeros.toString()
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fabListHerosPre.setOnClickListener {
            listHerosViewModel.getHerosPre()
        }
        fabListHerosPro.setOnClickListener {
            listHerosViewModel.getHerosPro()
        }
    }

    private fun setupRecycerView(heros: List<MarvelCharacters>) {
        recyclerViewListHeros.adapter = HerosRecyclerAdapter(heros)
        recyclerViewListHeros.layoutManager = LinearLayoutManager(requireContext())
    }
    private fun showSnackbar(msg: String) {
        if (!msg.isNullOrBlank())
            Snackbar.make(
                root_list_heros_layout,
                msg, Snackbar.LENGTH_LONG
            ).show()
    }

}