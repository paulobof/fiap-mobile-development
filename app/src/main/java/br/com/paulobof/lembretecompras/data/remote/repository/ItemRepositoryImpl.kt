package br.com.paulobof.lembretecompras.data.remote.repository

import br.com.paulobof.lembretecompras.data.remote.datasource.ItemRemoteDataSource
import br.com.paulobof.lembretecompras.domain.entity.NewItem
import br.com.paulobof.lembretecompras.domain.entity.RequestState
import br.com.paulobof.lembretecompras.domain.repository.ItemRepository

class ItemRepositoryImpl (
    private val itemRemoteDataSource: ItemRemoteDataSource
) : ItemRepository {

    override suspend fun create(newItem: NewItem): RequestState<NewItem> {
        return itemRemoteDataSource.create(newItem)
    }
}
