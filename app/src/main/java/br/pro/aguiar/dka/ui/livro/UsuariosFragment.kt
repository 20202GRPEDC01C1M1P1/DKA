package br.pro.aguiar.dka.ui.livro

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.pro.aguiar.dka.R
import br.pro.aguiar.dka.model.User
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.usuarios_fragment.*

class UsuariosFragment : Fragment() {


    private lateinit var viewModel: UsuariosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.usuarios_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UsuariosViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

}