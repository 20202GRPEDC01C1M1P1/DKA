package br.pro.aguiar.dka.social.ui.acesso

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {

    fun loginUser(
        email: String, senha: String
    ): Task<AuthResult> {
        var firebaseAuth = FirebaseAuth.getInstance()
        var task = firebaseAuth.signInWithEmailAndPassword(email, senha)
        return task
    }
}