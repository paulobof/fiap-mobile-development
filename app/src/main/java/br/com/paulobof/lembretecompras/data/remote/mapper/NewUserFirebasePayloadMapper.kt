package br.com.paulobof.lembretecompras.data.remote.mapper

import br.com.paulobof.lembretecompras.domain.entity.NewUser
import br.com.paulobof.lembretecompras.domain.entity.NewUserFirebasePayload
import br.com.paulobof.lembretecompras.domain.entity.User

object NewUserFirebasePayloadMapper {
    fun mapToNewUser (newUserFirebasePayload: NewUserFirebasePayload) = NewUser(
        name = newUserFirebasePayload. name ?: "",
        email = newUserFirebasePayload. email ?: "",
        phone = newUserFirebasePayload. phone ?: "",
        password = newUserFirebasePayload. password ?: ""
    )
    fun mapToNewUserFirebasePayload (newUser: NewUser) = NewUserFirebasePayload(
        name = newUser.name,
        email = newUser.email,
        phone = newUser.phone,
        password = newUser.password
    )
    fun mapToUser(newUserFirebasePayload: NewUserFirebasePayload ) = User(
        name = newUserFirebasePayload. name ?: ""
    )
}
