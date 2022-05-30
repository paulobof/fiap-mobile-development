package br.com.paulobof.lembretecompras.presentation.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.paulobof.lembretecompras.domain.entity.NewUser
import br.com.paulobof.lembretecompras.domain.entity.RequestState
import br.com.paulobof.lembretecompras.domain.entity.User
import br.com.paulobof.lembretecompras.domain.usecases.CreateUserUseCase
import kotlinx.coroutines.launch

class SignUpViewModel (
    private val createUserUseCase : CreateUserUseCase
) : ViewModel() {
    val newUserState = MutableLiveData<RequestState<User>>()
    fun create(name: String, email: String, phone: String, password:
    String) {
        viewModelScope.launch {
            newUserState .value = createUserUseCase .create(
                NewUser(
                    name,
                    email,
                    phone,
                    password
                )
            )
        }
    }
}
