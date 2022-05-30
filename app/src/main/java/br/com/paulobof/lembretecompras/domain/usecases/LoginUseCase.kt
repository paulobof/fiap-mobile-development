package br.com.paulobof.lembretecompras.domain.usecases

import br.com.paulobof.lembretecompras.domain.entity.RequestState
import br.com.paulobof.lembretecompras.domain.entity.User
import br.com.paulobof.lembretecompras.domain.entity.UserLogin
import br.com.paulobof.lembretecompras.domain.repository.UserRepository

class LoginUseCase(
    private val userRepository: UserRepository
) {
    suspend fun doLogin(userLogin: UserLogin): RequestState<User> =
        userRepository.doLogin(userLogin)

}
