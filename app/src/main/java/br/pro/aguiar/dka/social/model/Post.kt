package br.pro.aguiar.dka.social.model

import com.google.firebase.firestore.DocumentId

class Post (
    var titulo: String,
    var conteudo: String,
    var autor: String,
    var curtidas: Int = 0,
    @DocumentId var id: String? = null
) {
    fun getResumo() : String {
        return if (conteudo.length <= 30) conteudo
            else conteudo.substring(30) + "..."
    }
}