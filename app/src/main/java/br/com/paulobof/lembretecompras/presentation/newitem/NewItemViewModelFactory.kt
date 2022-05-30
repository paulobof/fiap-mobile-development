package br.com.paulobof.lembretecompras.presentation.newitem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.paulobof.lembretecompras.domain.usecases.CreateItemUseCase

class NewItemViewModelFactory (
    private val createItemUseCase: CreateItemUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(CreateItemUseCase::class.java).newInstance(createItemUseCase)
    }
}