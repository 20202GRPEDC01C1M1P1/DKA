package br.pro.aguiar.dka.ui.usuario.list

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class UsuariosViewModel : ViewModel() {
    fun all(): CollectionReference {
        var db = FirebaseFirestore.getInstance()
        return db
            .collection("users")
        /*retrun collection
//            .whereGreaterThan("age", 17)
//            .orderBy("name", Query.Direction.DESCENDING)
//            .limit(3)
            //.get()
//            .addSnapshotListener { snapshot, error ->
//                if (error != null){
//                    //
//                } else if (snapshot != null){
//                    Log.i("Snapshot", "Dados Atualizados")
//                }
//            }
        //return task*/
    }
}