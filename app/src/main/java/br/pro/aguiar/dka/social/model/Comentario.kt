package br.pro.aguiar.dka.social.model

import com.google.firebase.firestore.DocumentId

class Comentario (
    var autor: String? = null,
    var conteudo: String? = null,
    @DocumentId var id: String? = null
){
    override fun toString(): String {
        return "$autor: $conteudo"
    }
}