package com.tba.todox.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.stateIn



sealed interface HomeUiState {
    data class Success(val value1: Int, val value2: String) : HomeUiState
    data object Loading : HomeUiState
    data class Error(val error: Throwable) : HomeUiState
}

sealed interface HomeUiEvent {
    data object SomeInteraction : HomeUiEvent
    data class AnotherInteraction(val value: Int) : HomeUiEvent
}

class HomeViewModel() : ViewModel() {
    private val value1 = MutableStateFlow(0)
    private val value2 = MutableStateFlow("")

    val uiState = combine(value1, value2, HomeUiState::Success)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), HomeUiState.Loading)

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.AnotherInteraction -> {}
            HomeUiEvent.SomeInteraction -> {}
        }
    }
}