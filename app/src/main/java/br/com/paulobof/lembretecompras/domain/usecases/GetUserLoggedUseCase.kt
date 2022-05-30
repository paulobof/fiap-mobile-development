package br.com.paulobof.lembretecompras.domain.usecases

import br.com.paulobof.lembretecompras.domain.entity.RequestState
import br.com.paulobof.lembretecompras.domain.entity.User
import br.com.paulobof.lembretecompras.domain.repository.UserRepository

class GetUserLoggedUseCase(
    private val userRepository: UserRepository
) {
    suspend fun getUserLogged(): RequestState<User> =
        userRepository.getUserLogged()
}
