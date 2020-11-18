package br.pro.aguiar.retrofit.ui.card.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.pro.aguiar.retrofit.R

class ListCardFragment : Fragment() {

    companion object {
        fun newInstance() = ListCardFragment()
    }

    private lateinit var viewModel: ListCardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_card_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListCardViewModel::class.java)
        // TODO: Use the ViewModel
    }

}