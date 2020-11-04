package br.pro.aguiar.dka.social.model

import com.google.firebase.firestore.DocumentId

class Post (
    var titulo: String? = null,
    var conteudo: String? = null,
    var autor: String? = null,
    var curtidas: Int = 0,
    @DocumentId var id: String? = null
) {
    fun getResumo() : String {
        if (conteudo != null)
            return if (conteudo!!.length < 100) conteudo!!
                else conteudo!!.substring(0,100) + "..."
        else
            return ""
    }
}