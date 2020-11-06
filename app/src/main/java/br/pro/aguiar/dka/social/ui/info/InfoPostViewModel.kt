package br.pro.aguiar.dka.social.ui.info

import androidx.lifecycle.ViewModel
import br.pro.aguiar.dka.social.model.Post
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class InfoPostViewModel: ViewModel() {

    var db = FirebaseFirestore.getInstance()
    var collection = db.collection("posts")

    //collection.get()
    //collection.addSnapshotListener { ... }

    //collection.document(..).get()
    //collection.document(..).addSnapshotListener { ... }

    fun curtir(post: Post) {
        var document = collection.document(post.id!!)
        document.set(
            hashMapOf(
                "curtidas" to post.curtidas++
            ),
            SetOptions.merge()
        )
    }

    fun get(id: String): DocumentReference {
        var document = collection.document(id)
        return document
    }
}