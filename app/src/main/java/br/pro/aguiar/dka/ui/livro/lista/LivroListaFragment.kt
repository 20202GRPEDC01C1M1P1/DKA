package br.pro.aguiar.dka.ui.livro.lista

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import br.pro.aguiar.dka.R
import br.pro.aguiar.dka.model.Livro
import br.pro.aguiar.dka.room.AppDatabase
import br.pro.aguiar.dka.room.RoomDatabase
import kotlinx.android.synthetic.main.livro_lista_fragment.*

class LivroListaFragment : Fragment() {
    private lateinit var viewModel: LivroListaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.livro_lista_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LivroListaViewModel::class.java)

        val db = AppDatabase.getInstance(requireActivity().applicationContext)
//        LivroListaAsync().execute(db)

        listViewLivros.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            viewModel.all(db)
        )

        fabForm.setOnClickListener {
            findNavController().navigate(R.id.livroFormFragment)
        }
    }

    inner class LivroListaAsync
        : AsyncTask<RoomDatabase, Unit, Array<Livro>>() {

        // Main Thread
        override fun onPreExecute() {
            super.onPreExecute()
            // progressBar
        }
        override fun doInBackground // Segundo Plano
                    (vararg db: RoomDatabase?): Array<Livro> {
            return viewModel.all(db[0]!!)
        }
        // Main Thread
        override fun onPostExecute(result: Array<Livro>?) {
            super.onPostExecute(result)
            if (result != null)
                listViewLivros.adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_list_item_1,
                    result
                )
            else
                Toast.makeText(
                    requireContext(),
                    "Consulta não realizada.",
                    Toast.LENGTH_LONG
                ).show()
        }
    }


}