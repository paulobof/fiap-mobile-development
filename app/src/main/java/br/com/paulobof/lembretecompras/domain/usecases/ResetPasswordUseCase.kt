package br.com.paulobof.lembretecompras.domain.usecases

import br.com.paulobof.lembretecompras.domain.entity.RequestState
import br.com.paulobof.lembretecompras.domain.repository.UserRepository

class ResetPasswordUseCase(
    private val userRepository: UserRepository
) {
    suspend fun resetPassword(email: String): RequestState<String> =
        userRepository.resetPassword(email)
}

