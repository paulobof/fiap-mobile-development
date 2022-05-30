package br.com.paulobof.lembretecompras.data.remote.datasource

import br.com.paulobof.lembretecompras.domain.entity.RequestState
import br.com.paulobof.lembretecompras.domain.entity.User
import br.com.paulobof.lembretecompras.domain.entity.UserLogin
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.tasks.await

@ExperimentalCoroutinesApi
class UserRemoteFirebaseDataSourceImpl(
    private val firebaseAuth: FirebaseAuth
) : UserRemoteDataSource {

    override suspend fun getUserLogged(): RequestState<User> {
        FirebaseAuth.getInstance().currentUser?.reload()
        val firebaseUser = firebaseAuth.currentUser
        return if (firebaseUser == null) {
            RequestState.Error(Exception("Usuário deslogado"))
        } else {
            RequestState.Success(
                User(
                    firebaseUser.displayName ?: "Não identificado"
                )
            )
        }
    }

    override suspend fun doLogin(userLogin: UserLogin): RequestState<User> {
        return try {
            firebaseAuth.signInWithEmailAndPassword(
                userLogin.email,
                userLogin.password
            ).await()
            val firebaseUser = firebaseAuth.currentUser
            if (firebaseUser?.email != null) {
                RequestState.Success(User(firebaseUser.displayName ?: "Não identificado"))
            } else {
                RequestState.Error(Exception("Usuario ou senha inválida"))
            }
        } catch (excpetion: java.lang.Exception) {
            RequestState.Error(excpetion)
        }
    }
    override suspend fun resetPassword(email: String):
            RequestState<String> {
        return try{
            firebaseAuth.sendPasswordResetEmail(email).await()
            RequestState.Success("Verifique sua caixa de e-mail")
        } catch (e: java.lang.Exception) {
            RequestState.Error(e)
        }
    }

}
