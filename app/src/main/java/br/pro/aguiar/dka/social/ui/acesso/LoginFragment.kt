package br.pro.aguiar.dka.social.ui.acesso

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.pro.aguiar.dka.R
import br.pro.aguiar.dka.social.SocialActivity
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        btnAcessar.setOnClickListener {
            var email = editTextLoginEmail.text.toString()
            var senha = editTextLoginSenha.text.toString()

            viewModel.loginUser(email, senha)
                .addOnSuccessListener {
                    startActivity(
                        Intent(activity, SocialActivity::class.java)
                    )
                }
                .addOnFailureListener {
                    Toast.makeText(
                        requireContext(),
                        it.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
        }

        btnCadastrarSe.setOnClickListener {
            findNavController().navigate(R.id.cadastroFragment)
        }
    }

}