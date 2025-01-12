package com.etf.testing.workshop.presentation.dogs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.etf.testing.workshop.domain.DogsRepository
import com.etf.testing.workshop.presentation.common.DataState
import com.etf.testing.workshop.presentation.dogs.model.DogBreedsUi
import com.etf.testing.workshop.presentation.dogs.model.toUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DogsListViewModel(
    private val dogsRepository: DogsRepository
): ViewModel() {

    private val _state: MutableStateFlow<DataState<DogBreedsUi>> = MutableStateFlow(DataState.Loading<DogBreedsUi>(null))
    val state: StateFlow<DataState<DogBreedsUi>> = _state

    fun fetchBreedsList() {
        _state.value = DataState.Loading(_state.value.data)
        viewModelScope.launch {
            dogsRepository.getBreedsList()
                .onSuccess { breeds ->
                    _state.value = DataState.Success(breeds.map { it.toUi() })
                }
                .onFailure { error ->
                    _state.value = DataState.Error(error)
                }
        }
    }
}
