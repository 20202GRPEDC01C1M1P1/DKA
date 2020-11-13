package br.pro.aguiar.dka.ui.usuario.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import br.pro.aguiar.dka.R
import br.pro.aguiar.dka.UsuarioViewModel
import br.pro.aguiar.dka.model.User
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.usuario_cadastro_fragment.*

class UsuarioCadastroFragment : Fragment() {

    private lateinit var viewModel: UsuarioCadastroViewModel
    private lateinit var usuarioViewModel: UsuarioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.usuario_cadastro_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            usuarioViewModel = ViewModelProviders.of(it)
                .get(UsuarioViewModel::class.java)
            if (usuarioViewModel.usuario != null){
                var user = usuarioViewModel.usuario
                addInputInfo(user)
            }
        }
        viewModel = ViewModelProviders.of(this)
            .get(UsuarioCadastroViewModel::class.java)
//        btnUsuarioCadastrar.setOnClickListener {
//            var user = User(
//                editTextUsuarioCadastroNome.text.toString(),
//                editTextUsuarioCadastroIdade.text.toString().toInt(),
//                editTextUsuarioCadastroAnoNascimento.text.toString(),
//                "cidade",
//                editTextUsuarioCadastroEmail.text.toString()
//            )
//            viewModel.store(user)
//                .addOnSuccessListener {
//                    Snackbar.make(
//                        root_view_usuario_cadastro,
//                        "Cadastro realizado com sucesso.",
//                        Snackbar.LENGTH_LONG
//                    ).show()
//                    findNavController().popBackStack()
//                }
//                .addOnFailureListener {
//                    Snackbar.make(
//                        root_view_usuario_cadastro,
//                        it.message.toString(),
//                        Snackbar.LENGTH_LONG
//                    ).show()
//                }
//        }
        btnUsuarioExcluir.setOnClickListener {
            viewModel
                .delete(usuarioViewModel.usuario!!)
                .addOnSuccessListener {
                    Snackbar
                        .make(
                            root_view_usuario_cadastro,
                            "Usuário excluído com sucesso.",
                            Snackbar.LENGTH_LONG
                        ).show()
                    findNavController().popBackStack()
                }
                .addOnFailureListener {
                    Snackbar
                        .make(
                            root_view_usuario_cadastro,
                            it.message.toString(),
                            Snackbar.LENGTH_LONG
                        ).show()
                }
        }
    }

    private fun addInputInfo(user: User?) {
        editTextUsuarioCadastroNome.setText(user?.name)
        editTextUsuarioCadastroIdade.setText(user?.age.toString())
        editTextUsuarioCadastroAnoNascimento.setText(user?.burn.toString())
        editTextUsuarioCadastroEmail.setText(user?.id)
        btnUsuarioExcluir.isEnabled = true
        btnUsuarioExcluir.visibility = View.VISIBLE
    }

}