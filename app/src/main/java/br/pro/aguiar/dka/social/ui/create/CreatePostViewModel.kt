package br.pro.aguiar.dka.social.ui.create

import androidx.lifecycle.ViewModel
import br.pro.aguiar.dka.social.model.Post
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class CreatePostViewModel : ViewModel() {
    fun store(
        titulo: String,
        conteudo: String
    ): Task<DocumentReference> {
        var post = Post(
            titulo, conteudo, "Aguiar"
        )
        var db = FirebaseFirestore.getInstance()
        var collection = db.collection("posts")
        var task = collection.add(post)
        return task
    }
}