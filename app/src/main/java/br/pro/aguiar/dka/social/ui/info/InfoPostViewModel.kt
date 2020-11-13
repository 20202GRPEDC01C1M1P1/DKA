package br.pro.aguiar.dka.social.ui.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.pro.aguiar.dka.social.model.Comentario
import br.pro.aguiar.dka.social.model.Post
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*

class InfoPostViewModel: ViewModel() {

    var db = FirebaseFirestore.getInstance()
    var collection = db.collection("posts")
    var comentarios = MutableLiveData<MutableList<Comentario>>()
    var post = MutableLiveData<Post>()

    fun curtir(post: Post) {
        var document = collection.document(post.id!!)
        document.set(
            hashMapOf(
                "curtidas" to post.curtidas++
            ),
            SetOptions.merge()
        )
    }

    fun get(id: String) {
        var document = collection.document(id)
        document.addSnapshotListener { documentoPost, error ->
            post.value = documentoPost!!.toObject(Post::class.java)
        }
    }

    fun getComentarios(post: Post){
        collection
            .document(post.id!!)
            .collection("comentarios")
            .addSnapshotListener { documentosComentarios, error ->
                comentarios.value =
                    documentosComentarios!!
                        .toObjects(Comentario::class.java)
                        .toMutableList()
            }
    }

    fun getAutor(uid: String): DocumentReference {
        var collection = db.collection("users")
        var document = collection.document(uid)
        return document
    }
}