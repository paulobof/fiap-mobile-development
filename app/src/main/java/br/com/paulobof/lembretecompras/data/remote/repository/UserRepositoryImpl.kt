package br.com.paulobof.lembretecompras.data.remote.repository

import br.com.paulobof.lembretecompras.data.remote.datasource.UserRemoteDataSource
import br.com.paulobof.lembretecompras.domain.entity.NewUser
import br.com.paulobof.lembretecompras.domain.entity.RequestState
import br.com.paulobof.lembretecompras.domain.entity.User
import br.com.paulobof.lembretecompras.domain.entity.UserLogin
import br.com.paulobof.lembretecompras.domain.repository.UserRepository

data class UserRepositoryImpl(
    val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun getUserLogged(): RequestState<User> {
        return userRemoteDataSource.getUserLogged()
    }

    override suspend fun doLogin(userLogin: UserLogin):
            RequestState<User> {
        return userRemoteDataSource.doLogin(userLogin)
    }

    override suspend fun resetPassword(email: String):
            RequestState<String> {
        return userRemoteDataSource.resetPassword(email)
    }

    override suspend fun create(newUser: NewUser): RequestState<User> {
        return userRemoteDataSource.create(newUser)
    }

}
