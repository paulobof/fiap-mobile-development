package br.com.paulobof.lembretecompras.presentation.base.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import br.com.paulobof.lembretecompras.R
import br.com.paulobof.lembretecompras.data.remote.datasource.UserRemoteFirebaseDataSourceImpl
import br.com.paulobof.lembretecompras.data.remote.repository.UserRepositoryImpl
import br.com.paulobof.lembretecompras.domain.entity.RequestState
import br.com.paulobof.lembretecompras.domain.usecases.GetUserLoggedUseCase
import br.com.paulobof.lembretecompras.presentation.base.BaseFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.ExperimentalCoroutinesApi

const val NAVIGATION_KEY = "NAV_KEY"
@ExperimentalCoroutinesApi
abstract class BaseAuthFragment : BaseFragment() {
    private val baseAuthViewModel: BaseAuthViewModel by lazy {
        ViewModelProvider(
            this,

            BaseViewModelFactory(GetUserLoggedUseCase(UserRepositoryImpl(
                UserRemoteFirebaseDataSourceImpl(
                    FirebaseAuth.getInstance()
                )
            )))
        ).get(BaseAuthViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        registerObserver()
        baseAuthViewModel.getUserLogged()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun registerObserver() {
        baseAuthViewModel.userLogged.observe(viewLifecycleOwner, Observer {
                result ->
            when (result) {
                is RequestState.Loading -> {
                    showLoading()
                }
                is RequestState.Success -> {
                    hideLoading()
                }
                is RequestState.Error -> {
                    findNavController(this).navigate(
                        R.id.login_graph, bundleOf(
                            NAVIGATION_KEY to
                                    findNavController(this).currentDestination?.id
                        )
                    )
                    hideLoading()
                }
            }
        })
    }
}
