package br.pro.aguiar.dka.ui.usuario.list

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class UsuariosViewModel : ViewModel() {
    fun all(): Task<QuerySnapshot> {
        var db = FirebaseFirestore.getInstance()
        var task = db
            .collection("users")
//            .whereGreaterThan("age", 17)
//            .orderBy("name", Query.Direction.DESCENDING)
//            .limit(3)
            .get()
        return task
    }
}