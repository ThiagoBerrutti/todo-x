package com.tba.todox.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update


sealed interface HomeUiState {
    data class Success(val value1: Int, val value2: String, val isAddTaskModalVisible:Boolean) : HomeUiState
    data object Loading : HomeUiState
    data class Error(val error: Throwable) : HomeUiState
}

sealed interface HomeUiEvent {
    data object AddTaskClick : HomeUiEvent
    data object AddTaskModalDismissed : HomeUiEvent
    data object SomeInteraction : HomeUiEvent
    data class AnotherInteraction(val value: Int) : HomeUiEvent
}

class HomeViewModel() : ViewModel() {
    fun test() = "Testando"
    private val value1 = MutableStateFlow(0)
    private val value2 = MutableStateFlow("")
    private val isAddTaskModalVisible = MutableStateFlow(false)

    val uiState = combine(value1, value2, isAddTaskModalVisible, HomeUiState::Success)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), HomeUiState.Loading)

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.AnotherInteraction -> {}
            HomeUiEvent.SomeInteraction -> {value1.update { it + 1 }}
            HomeUiEvent.AddTaskClick -> { isAddTaskModalVisible.update { true }}
            HomeUiEvent.AddTaskModalDismissed -> { isAddTaskModalVisible.update { false }}
        }
    }
}