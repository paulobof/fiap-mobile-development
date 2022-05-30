package br.com.paulobof.lembretecompras.domain.usecases

import br.com.paulobof.lembretecompras.domain.entity.NewUser
import br.com.paulobof.lembretecompras.domain.entity.RequestState
import br.com.paulobof.lembretecompras.domain.entity.User
import br.com.paulobof.lembretecompras.domain.repository.UserRepository

class CreateUserUseCase(
    private val userRepository: UserRepository
) {
    suspend fun create(newUser: NewUser): RequestState<User> =
        userRepository.create(newUser)
}
