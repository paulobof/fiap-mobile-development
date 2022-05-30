package br.com.paulobof.lembretecompras.presentation.login

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.paulobof.lembretecompras.R
import br.com.paulobof.lembretecompras.data.remote.datasource.UserRemoteFirebaseDataSourceImpl
import br.com.paulobof.lembretecompras.data.remote.repository.UserRepositoryImpl
import br.com.paulobof.lembretecompras.domain.entity.RequestState
import br.com.paulobof.lembretecompras.domain.usecases.LoginUseCase
import br.com.paulobof.lembretecompras.domain.usecases.ResetPasswordUseCase
import br.com.paulobof.lembretecompras.presentation.base.BaseFragment
import br.com.paulobof.lembretecompras.presentation.base.auth.NAVIGATION_KEY
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class LoginFragment : BaseFragment() {
    override val layout = R.layout.fragment_login
    private lateinit var tvSubTitleSignUp: TextView
    private lateinit var containerLogin: LinearLayout
    private lateinit var tvResetPassword: TextView
    private lateinit var tvNewAccount: TextView
    private lateinit var btLogin: Button
    private lateinit var etEmailLogin: EditText
    private lateinit var etPasswordLogin: EditText

    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProvider(
            this,
            LoginViewModelFactory(
                LoginUseCase(
                    UserRepositoryImpl(
                        UserRemoteFirebaseDataSourceImpl(FirebaseAuth.getInstance())
                    )
                ),
                ResetPasswordUseCase(
                    UserRepositoryImpl(
                        UserRemoteFirebaseDataSourceImpl(FirebaseAuth.getInstance())
                    )
                )
            )
        ).get(LoginViewModel::class.java)
    }


    override fun onViewCreated (view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView(view)
        registerObserver()
        registerBackPressedAction()
    }

    private fun setUpView(view: View) {
        tvSubTitleSignUp = view.findViewById(R.id.tvSubTitleLogin)
        containerLogin = view.findViewById(R.id.containerLogin)
        tvResetPassword = view.findViewById(R.id.tvResetPassword)
        tvNewAccount = view.findViewById(R.id.tvNewAccount)
        btLogin = view.findViewById(R.id.btLogin)
        etEmailLogin = view.findViewById(R.id.etEmailLogin)
        etPasswordLogin = view.findViewById(R.id.etPasswordLogin)
        btLogin.setOnClickListener {
            loginViewModel.doLogin(
                etEmailLogin.text.toString(),
                etPasswordLogin.text.toString()
            )
        }

        tvResetPassword .setOnClickListener {
            if(etEmailLogin.text.toString().isEmpty()) {
                showMessage("Informe o e-mail que deseja alterar a senha")
            } else {
                loginViewModel.resetPassword(etEmailLogin.text.toString())
            }
        }
        tvNewAccount.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }
    private fun registerObserver() {
        loginViewModel.loginState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is RequestState.Success -> showSuccess()
                is RequestState.Error -> showError(it.throwable)
                is RequestState.Loading -> showLoading("Realizando a autenticação")
            }
        })

        loginViewModel.resetPasswordState.observe(viewLifecycleOwner, Observer
        {
            when (it) {
                is RequestState.Success -> {
                    hideLoading()
                    showMessage(it.data)
                }
                is RequestState.Error -> showError(it.throwable)
                is RequestState.Loading -> showLoading("Reenviando o e-mail para alteração")
            }
        })
    }

    private fun showSuccess() {
        hideLoading()
        val navIdForArguments = arguments?.getInt(NAVIGATION_KEY)
        if (navIdForArguments == null) {
            findNavController().navigate(R.id.main_nav_graph)
        } else {
            findNavController().popBackStack(navIdForArguments, false)
        }
    }

    private fun showError(throwable: Throwable) {
        hideLoading()
        etEmailLogin.error = null
        etPasswordLogin.error = null
        showMessage(throwable.message)
    }

    private fun registerBackPressedAction() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

}

