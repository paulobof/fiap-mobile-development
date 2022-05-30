package br.com.paulobof.lembretecompras.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.paulobof.lembretecompras.domain.entity.RequestState
import br.com.paulobof.lembretecompras.domain.entity.User
import br.com.paulobof.lembretecompras.domain.entity.UserLogin
import br.com.paulobof.lembretecompras.domain.usecases.LoginUseCase
import br.com.paulobof.lembretecompras.domain.usecases.ResetPasswordUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase ,
    private val resetPasswordUseCase: ResetPasswordUseCase

) : ViewModel() {
    val loginState = MutableLiveData<RequestState<User>>()

    fun doLogin(email: String, password: String) {
        viewModelScope.launch {
            loginState.value = loginUseCase.doLogin(
                UserLogin(email, password)
            )
        }
    }

    val resetPasswordState = MutableLiveData<RequestState<String>>()

    fun resetPassword(email: String) {
        viewModelScope.launch {
            resetPasswordState.value =
                resetPasswordUseCase.resetPassword(email)
        }
    }

}
