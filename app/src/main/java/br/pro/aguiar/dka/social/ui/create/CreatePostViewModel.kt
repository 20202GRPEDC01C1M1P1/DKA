package br.pro.aguiar.dka.social.ui.create

import androidx.lifecycle.ViewModel
import br.pro.aguiar.dka.social.model.Post
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class CreatePostViewModel : ViewModel() {
    var db = FirebaseFirestore.getInstance()
    var collection = db.collection("posts")

    fun store(
        titulo: String,
        conteudo: String
    ): Task<DocumentReference> {
//        FirebaseAuth.getInstance().currentUser?.
        var post = Post(
            titulo, conteudo, FirebaseAuth.getInstance().currentUser?.uid
        )
        var task = collection.add(post)
        return task
    }

    fun update(
        titulo: String,
        conteudo: String,
        postId: String): Task<Void> {
            var post = Post(
                titulo, conteudo, FirebaseAuth.getInstance().currentUser?.uid
            )
            var task = collection.document(postId).set(post)
            return task
    }

    fun delete(postId: String): Task<Void> {
        var task = collection.document(postId).delete()
        return task
    }
}