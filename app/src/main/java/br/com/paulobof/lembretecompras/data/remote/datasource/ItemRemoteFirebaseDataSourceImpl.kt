package br.com.paulobof.lembretecompras.data.remote.datasource

import br.com.paulobof.lembretecompras.data.remote.mapper.NewItemFirebasePayloadMapper
import br.com.paulobof.lembretecompras.data.remote.mapper.NewUserFirebasePayloadMapper
import br.com.paulobof.lembretecompras.domain.entity.NewItem
import br.com.paulobof.lembretecompras.domain.entity.NewItemFirebasePayload
import br.com.paulobof.lembretecompras.domain.entity.RequestState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.tasks.await

@ExperimentalCoroutinesApi
class ItemRemoteFirebaseDataSourceImpl(
    private val firebaseFirestore: FirebaseFirestore
) : ItemRemoteDataSource {
    override suspend fun create(newItem: NewItem): RequestState<NewItem> {
            val newItemFirebasePayload =
                NewItemFirebasePayloadMapper.mapToNewItemFirebasePayload(newItem)
            return try {
                firebaseFirestore.collection("items")
                    .document(newItem.produto)
                    .set(newItemFirebasePayload)
                    .await()
                RequestState.Success(newItem)
            } catch (e: Exception) {
                RequestState.Error(e)
            }
        }
    }

