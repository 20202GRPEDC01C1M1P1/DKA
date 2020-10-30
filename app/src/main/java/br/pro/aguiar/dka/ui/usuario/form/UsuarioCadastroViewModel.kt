package br.pro.aguiar.dka.ui.usuario.form

import androidx.lifecycle.ViewModel
import br.pro.aguiar.dka.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class UsuarioCadastroViewModel : ViewModel() {
    fun store(user: User): Task<Void> {
        var db = FirebaseFirestore.getInstance()
        var task = db
            .collection("users")
            .document(user.id!!)
            .set(user)
            //.add(user)
        return task
    }

    fun delete(user: User): Task<Void> {
        var db = FirebaseFirestore.getInstance()
        var task = db
            .collection("users")
            .document(user.id!!)
            .delete()
        return task
    }

}