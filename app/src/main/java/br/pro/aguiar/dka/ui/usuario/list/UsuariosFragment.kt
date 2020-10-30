package br.pro.aguiar.dka.ui.usuario.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import br.pro.aguiar.dka.R
import br.pro.aguiar.dka.UsuarioViewModel
import br.pro.aguiar.dka.model.User
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.usuarios_fragment.*

class UsuariosFragment : Fragment() {


    private lateinit var viewModel: UsuariosViewModel
    private lateinit var usuarioViewModel: UsuarioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.usuarios_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let{
            usuarioViewModel = ViewModelProviders.of(it).get(UsuarioViewModel::class.java)
        }

        fabUsuarioCadastro.setOnClickListener {
            usuarioViewModel.usuario = null
            findNavController().navigate(R.id.usuarioCadastroFragment)
        }

        viewModel = ViewModelProviders.of(this).get(UsuariosViewModel::class.java)
        viewModel
            .all()
            .addOnSuccessListener {
                var listaUsuarios = it.toObjects(User::class.java)
                if (!listaUsuarios.isNullOrEmpty()) {
                    listViewUsuariosLista.adapter =
                        ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_list_item_1,
                            it.toObjects(User::class.java)
                        )
                    listViewUsuariosLista.setOnItemClickListener { adapterView, view, i, l ->
                        var user = listaUsuarios[i]
                        usuarioViewModel.usuario = user
                        findNavController().navigate(R.id.usuarioCadastroFragment)
                    }

                }
                else {
                    Snackbar.make(
                        frameLayout4,
                        "Lista de Usuários vazia!",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
            .addOnFailureListener {
                Log.e("FirestoreIndex", it.message.toString())
                Snackbar.make(
                    frameLayout4,
                    it.message.toString(),
                    Snackbar.LENGTH_LONG
                ).show()
            }
            .addOnCompleteListener {
                progressBarUsuariosLista.visibility = View.GONE
            }
    }

    /*
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*
        var db = FirebaseFirestore.getInstance()
        var task = db
            .collection("users")
            .document("thi@go.br")
            .get()
        task
            .addOnSuccessListener {
                var usuario = it.toObject(User::class.java)
                Snackbar.make(
                    frameLayout4,
                    usuario.toString(),
                    Snackbar.LENGTH_INDEFINITE
                ).show()
            }
            .addOnFailureListener {
                Toast.makeText(
                    requireContext(),
                    it.message,
                    Toast.LENGTH_LONG
                ).show()
            }
         */
/*
//        var usuario = User("Thiago Aguiar", 45)
//
//        var db = FirebaseFirestore.getInstance()
//
//        var task = db
//            .collection("users")
//            .document("thi@go.br")
//            .set(usuario)
//
//        task.addOnSuccessListener {
//            Toast.makeText(
//                requireContext(),
//                "Usuário persistido com Sucesso.",
//                Toast.LENGTH_LONG
//            ).show()
//        }
//            .addOnFailureListener {
//            Toast.makeText(
//                requireContext(),
//                it.message,
//                Toast.LENGTH_LONG
//            ).show()
//        }
 */
    }
*/
}