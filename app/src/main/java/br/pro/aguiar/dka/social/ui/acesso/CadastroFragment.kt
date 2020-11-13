package br.pro.aguiar.dka.social.ui.acesso

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.pro.aguiar.dka.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.cadastro_fragment.*

class CadastroFragment : Fragment() {

    private lateinit var viewModel: CadastroViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cadastro_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CadastroViewModel::class.java)

        btnCadastroSalvar.setOnClickListener {
            var nome = editTextCadastroNome.text.toString()
            var dataNascimento = editTextCadastroDataNascimento.text.toString()
            var cidade = editTextCadastroCidade.text.toString()
            var email = editTextCadastroEmail.text.toString()
            var senha = editTextCadastroSenha.text.toString()
            var reSenha = editTextCadastroReSenha.text.toString()

            if (senha.compareTo(reSenha) == 0) {

                var task = viewModel.createUser(email, senha)
                task
                    .addOnSuccessListener {
                        viewModel.storeUserInfo(
                            it.user!!.uid,
                            nome, dataNascimento, cidade
                        )
                            .addOnSuccessListener {
                                Toast.makeText(
                                    requireContext(),
                                    "$nome cadastrado com sucesso.",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                            .addOnFailureListener {
                                Toast.makeText(
                                    requireContext(),
                                    it.message,
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                            .addOnCompleteListener {
                                FirebaseAuth.getInstance().signOut()
                                findNavController().popBackStack()
                            }
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            requireContext(),
                            it.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }

            } else {
                Toast.makeText(
                    requireContext(),
                    "Senhas não conferem!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }

}