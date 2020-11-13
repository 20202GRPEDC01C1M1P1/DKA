package br.pro.aguiar.dka.social.ui.comentario

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.pro.aguiar.dka.model.User
import br.pro.aguiar.dka.social.model.Comentario
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class ComentarioPostViewModel : ViewModel() {

    var db = FirebaseFirestore.getInstance()
    var nomeUsuario = MutableLiveData<String>()
    var persistencia = MutableLiveData<Boolean>()
    var mensagem = MutableLiveData<String>()

    init {
        nomeUsuario.value = "Nome do usuário"
        mensagem.value = null
        persistencia.value = false
        getUsuario()
    }

    fun store(
        conteudo: String,
        postId: String
    ) {
        var comentario = Comentario(this.nomeUsuario.value, conteudo)
        var collection = db
            .collection("posts")
            .document(postId)
            .collection("comentarios")
        var task = collection.add(comentario)
        task
            .addOnSuccessListener {
                persistencia.value = true
            }
            .addOnFailureListener {
                mensagem.value = it.message
            }
    }

    /*fun store(
        autor: String,
        conteudo: String,
        postId: String
    ): Task<DocumentReference> {
        var comentario = Comentario(autor, conteudo)
        var collection = db
            .collection("posts")
            .document(postId)
            .collection("comentarios")
        var task = collection.add(comentario)
        return task
    }*/

    private fun getUsuario(){
        db.collection("users")
            .document(FirebaseAuth.getInstance().uid!!)
            .addSnapshotListener { user, error ->
                nomeUsuario.value = user!!.toObject(User::class.java)!!.name
            }
    }
}