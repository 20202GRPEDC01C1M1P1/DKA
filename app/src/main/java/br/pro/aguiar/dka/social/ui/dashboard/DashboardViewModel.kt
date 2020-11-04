package br.pro.aguiar.dka.social.ui.dashboard

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class DashboardViewModel : ViewModel() {
    fun all(): Task<QuerySnapshot> {
        var db = FirebaseFirestore.getInstance()
        var collection = db.collection("posts")
        var task = collection.get()
        return task
    }
}