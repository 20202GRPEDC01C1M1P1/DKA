package br.pro.aguiar.dka.social.ui.acesso

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CadastroViewModel : ViewModel() {

    fun createUser(email: String, senha: String): Task<AuthResult> {

        var firebaseAuth = FirebaseAuth.getInstance()
        var task = firebaseAuth.createUserWithEmailAndPassword(email, senha)

        return task
    }

    fun storeUserInfo(
        nome: String,
        uid: String
    ): Task<Void> {
        var firebaseFirestore = FirebaseFirestore.getInstance()
        var collection = firebaseFirestore.collection("users")
        var document = collection.document(uid)
        var task = document.set(
            mapOf(
                "name" to nome
            )
        )
        return task
    }
}