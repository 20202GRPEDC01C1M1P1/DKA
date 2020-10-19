package br.pro.aguiar.dka.ui.livro.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import br.pro.aguiar.dka.R
import br.pro.aguiar.dka.room.AppDatabase
import kotlinx.android.synthetic.main.livro_form_fragment.*

class LivroFormFragment : Fragment() {

    private lateinit var viewModel: LivroFormViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.livro_form_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LivroFormViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLivroSalvar.setOnClickListener {
            var livroTitulo = edtTxtLivroFormTitulo.text.toString()
            var livroAno = edtTxtLivroFormAno.text.toString().toInt()
            var livroEditoras = edtTxtLivroFormEditoras.text.toString()
            var livroAutores = edtTxtLivroFormAutores.text.toString()
            var db = AppDatabase.getInstance(requireContext().applicationContext)
            viewModel.store(db.livroDAO(), livroTitulo, livroAno, livroEditoras, livroAutores)
        }
    }

}