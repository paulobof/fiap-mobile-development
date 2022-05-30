package br.com.paulobof.lembretecompras.data.remote.datasource

import br.com.paulobof.lembretecompras.domain.entity.NewItem
import br.com.paulobof.lembretecompras.domain.entity.RequestState

interface ItemRemoteDataSource {
    suspend fun create(newItem: NewItem): RequestState<NewItem>
}