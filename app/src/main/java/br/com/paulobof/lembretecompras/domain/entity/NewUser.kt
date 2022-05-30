package br.com.paulobof.lembretecompras.domain.entity

import com.google.firebase.firestore.Exclude

data class NewUser(
    val name: String,
    val email: String,
    val phone: String,
    val password: String
)
