package br.com.paulobof.lembretecompras.data.remote.mapper

import br.com.paulobof.lembretecompras.domain.entity.*

object NewItemFirebasePayloadMapper {
    fun mapToNewItem (newItemFirebasePayload: NewItemFirebasePayload) = NewItem(
        produto = newItemFirebasePayload.produto ?: ""
    )
    fun mapToNewItemFirebasePayload (newItem: NewItem) = NewItemFirebasePayload(
        produto = newItem.produto
    )
}
