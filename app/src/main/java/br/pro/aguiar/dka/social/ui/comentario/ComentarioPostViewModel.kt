package br.pro.aguiar.dka.social.ui.comentario

import androidx.lifecycle.ViewModel
import br.pro.aguiar.dka.social.model.Comentario
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class ComentarioPostViewModel : ViewModel() {
    fun store(
        autor: String,
        conteudo: String,
        postId: String
    ): Task<DocumentReference> {
        var comentario = Comentario(autor, conteudo)
        var db = FirebaseFirestore.getInstance()
        var collection = db
            .collection("posts")
            .document(postId)
            .collection("comentarios")
        var task = collection.add(comentario)
        return task
    }
}