package br.com.paulobof.lembretecompras.domain.repository

import br.com.paulobof.lembretecompras.domain.entity.RequestState
import br.com.paulobof.lembretecompras.domain.entity.User
import br.com.paulobof.lembretecompras.domain.entity.UserLogin

interface UserRepository {

    suspend fun getUserLogged(): RequestState<User>

    suspend fun doLogin(userLogin: UserLogin): RequestState<User>

    suspend fun resetPassword(email: String): RequestState<String>

}
