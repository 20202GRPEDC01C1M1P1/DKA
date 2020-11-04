package br.pro.aguiar.dka.social.ui.create

import androidx.lifecycle.ViewModel
import br.pro.aguiar.dka.social.model.Post
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class CreatePostViewModel : ViewModel() {
    var db = FirebaseFirestore.getInstance()
    var collection = db.collection("posts")

    fun store(
        titulo: String,
        conteudo: String
    ): Task<DocumentReference> {
        var post = Post(
            titulo, conteudo, "Aguiar"
        )
        var task = collection.add(post)
        return task
    }

    fun update(
        titulo: String,
        conteudo: String,
        postId: String): Task<Void> {
            var post = Post(
                titulo, conteudo, "Aguiar"
            )
            var task = collection.document(postId).set(post)
            return task
    }
}