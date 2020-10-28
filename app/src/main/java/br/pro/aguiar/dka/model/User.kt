package br.pro.aguiar.dka.model

import com.google.firebase.firestore.DocumentId

class User (
    var name: String? = null,
    var age: Int? = null,
    @DocumentId var id: String? = null
) {
    override fun toString(): String {
        return "$id: $name $age"
    }
}
