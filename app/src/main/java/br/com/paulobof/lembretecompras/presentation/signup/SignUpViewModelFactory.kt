package br.com.paulobof.lembretecompras.presentation.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import br.com.paulobof.lembretecompras.domain.entity.NewUser
import br.com.paulobof.lembretecompras.domain.entity.RequestState
import br.com.paulobof.lembretecompras.domain.entity.User
import br.com.paulobof.lembretecompras.domain.usecases.CreateUserUseCase
import kotlinx.coroutines.launch

class SignUpViewModelFactory(
    private val createUserUseCase: CreateUserUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(CreateUserUseCase::class.java).newInstance(createUserUseCase)
    }
}

