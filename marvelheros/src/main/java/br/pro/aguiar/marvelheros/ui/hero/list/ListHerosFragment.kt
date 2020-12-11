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
            it.msg.observe(viewLifecycleOwner) { msg ->
                if (!msg.isNullOrBlank())
                    showSnackbar(msg)
            }
            it.heros.observe(viewLifecycleOwner) { heros ->
                recyclerViewListHeros.adapter = HerosRecyclerAdapter(heros)
                recyclerViewListHeros.layoutManager = LinearLayoutManager(requireContext())
            }
        }
        return view
    }

    private fun showSnackbar(msg: String) {
        Snackbar.make(
            root_list_heros_layout,
            msg, Snackbar.LENGTH_LONG
        ).show()
    }

}