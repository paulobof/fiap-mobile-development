package br.com.paulobof.lembretecompras.domain.usecases

import br.com.paulobof.lembretecompras.domain.entity.NewItem
import br.com.paulobof.lembretecompras.domain.entity.RequestState
import br.com.paulobof.lembretecompras.domain.repository.ItemRepository

class CreateItemUseCase(
    private val itemRepository: ItemRepository
) {
    suspend fun create(newItem: NewItem): RequestState<NewItem> =
        itemRepository.create(newItem)
}
