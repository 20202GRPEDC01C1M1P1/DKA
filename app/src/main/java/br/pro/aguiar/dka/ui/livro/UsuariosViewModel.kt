package br.pro.aguiar.dka.ui.livro

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class UsuariosViewModel : ViewModel() {
    fun all(): Task<QuerySnapshot> {
        var db = FirebaseFirestore.getInstance()
        var task = db
            .collection("users")
            .get()
        return task
    }
}