package br.com.paulobof.lembretecompras.domain.entity

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class NewItemFirebasePayload(
    val produto: String? = ""
)
