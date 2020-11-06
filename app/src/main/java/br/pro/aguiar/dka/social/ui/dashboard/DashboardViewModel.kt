package br.pro.aguiar.dka.social.ui.dashboard

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class DashboardViewModel : ViewModel() {

    var db = FirebaseFirestore.getInstance()
    var collection = db.collection("posts")

    fun all(): Task<QuerySnapshot> {
        var task = collection
            .orderBy("curtidas", Query.Direction.DESCENDING)
            .limit(10).get()
        return task
    }

    fun selectByTitle(titulo: String): Task<QuerySnapshot> {
        var task = collection.whereEqualTo("titulo", titulo).get()
        return task
    }

}