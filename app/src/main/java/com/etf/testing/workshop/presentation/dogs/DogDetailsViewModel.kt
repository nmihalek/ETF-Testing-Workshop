package com.etf.testing.workshop.presentation.dogs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.etf.testing.workshop.domain.DogsRepository
import com.etf.testing.workshop.domain.model.Breed
import com.etf.testing.workshop.presentation.common.DataState
import com.etf.testing.workshop.presentation.dogs.model.DogUi
import com.etf.testing.workshop.presentation.dogs.model.toUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DogDetailsViewModel(
    val breed: Breed,
    private val dogsRepository: DogsRepository
): ViewModel() {

    private val _state: MutableStateFlow<DataState<DogUi>> = MutableStateFlow(DataState.Loading(null))
    val state: StateFlow<DataState<DogUi>> = _state

    fun fetchBreedDetails() {
        viewModelScope.launch {
            dogsRepository.getBreedDetails(breed)
                .onSuccess { breedDetails ->
                    _state.value = DataState.Success(breedDetails.toUi())
                }
                .onFailure { error ->
                    _state.value = DataState.Error(error)
                }
        }
    }
}
