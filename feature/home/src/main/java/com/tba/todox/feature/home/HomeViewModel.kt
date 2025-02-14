package com.tba.todox.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tba.todox.core.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime


sealed interface HomeUiState {
    data class Success(val tasks: List<Task>, val isAddTaskModalVisible: Boolean) : HomeUiState
    data object Loading : HomeUiState
    data class Error(val error: Throwable) : HomeUiState
}

sealed interface HomeUiEvent {
    data object AddTaskClick : HomeUiEvent
    data object AddTaskModalDismissed : HomeUiEvent
    data class ClickTask(val index:Int): HomeUiEvent
}

class HomeViewModel() : ViewModel() {
    private val tasks = MutableStateFlow<List<Task>>(MockTasks)

    private val isAddTaskModalVisible = MutableStateFlow(false)

    val uiState = combine(tasks, isAddTaskModalVisible, HomeUiState::Success)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), HomeUiState.Loading)

    fun removeTask(index: Int) {
        viewModelScope.launch {
            tasks.update {
                it.filterIndexed { i, _ -> i != index }
            }
        }
    }

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            HomeUiEvent.AddTaskClick -> {
                isAddTaskModalVisible.update { true }
            }

            HomeUiEvent.AddTaskModalDismissed -> {
                isAddTaskModalVisible.update { false }
            }

            is HomeUiEvent.ClickTask -> {
                removeTask(event.index)
            }
        }
    }
}

val MockTasks: List<Task> = listOf(
    Task(title = "Do math homework", priority = null),
    Task(title = "Estudar programacao", priority = 2, dateTime = LocalDateTime.of(2025,2,13,14,52)),
    Task(title = "Limpar o quarto", priority = 3),
    Task(title = "Arrumar as malas ", priority = 3, dateTime = LocalDateTime.of(2025,2,15,9,2)),
    Task(
        title = "Reunião com o Fulano",
        dateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 30)),
        priority = 3
    ),
    Task(
        title = "Limpar o quarto",
        priority = 5,
        dateTime = LocalDateTime.of(2025, 2, 18, 14, 2)
    )

).shuffled()