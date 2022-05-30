package br.com.paulobof.lembretecompras.presentation.newitem

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.paulobof.lembretecompras.domain.entity.NewItem
import br.com.paulobof.lembretecompras.domain.entity.RequestState
import br.com.paulobof.lembretecompras.domain.usecases.CreateItemUseCase
import kotlinx.coroutines.launch

class NewItemViewModel (
    private val createItemUseCase : CreateItemUseCase
) : ViewModel() {

    val newItemState = MutableLiveData<RequestState<NewItem>>()

    fun create (produto: String) {
        viewModelScope.launch {
            newItemState.value = createItemUseCase .create(
                NewItem(
                    produto)
            )
        }
    }
}