package br.com.paulobof.lembretecompras.domain.repository

import br.com.paulobof.lembretecompras.domain.entity.NewItem
import br.com.paulobof.lembretecompras.domain.entity.RequestState

interface ItemRepository {

    suspend fun create(newItem: NewItem): RequestState<NewItem>

}