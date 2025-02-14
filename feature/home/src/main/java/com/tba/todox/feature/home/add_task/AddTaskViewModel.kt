package com.tba.todox.feature.home.add_task

import android.util.Log
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tba.todox.core.model.Task
import com.tba.todox.feature.home.add_task.usecase.ValidateTask
import com.tba.todox.feature.home.add_task.usecase.ValidateTask.ValidationField
import com.tba.todox.feature.home.add_task.usecase.ValidationResult
import com.tba.todox.feature.home.add_task.usecase.validateDate
import com.tba.todox.feature.home.add_task.usecase.validateTime
import com.tba.todox.feature.home.add_task.usecase.validateTitle
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime


@Stable
data class AddTaskState(
    val title: String = "",
    val description: String = "",
    val date: LocalDate? = null,
    val time: LocalTime? = null,
    val priority: Int? = null,

    val titleErrors: List<String>? = null,
    val timeErrors: List<String>? = null,
    val dateErrors: List<String>? = null,
    val descriptionErrors: List<String>? = null,
    val priorityErrors: List<String>? = null,

    val isValidated:Boolean = false
)

fun AddTaskState.isValid() = timeErrors.isNullOrEmpty() &&
        titleErrors.isNullOrEmpty() &&
        dateErrors.isNullOrEmpty() &&
        descriptionErrors.isNullOrEmpty()


fun AddTaskState.toTask() =
    Task(
        title = title,
        description = description,
        dateTime = LocalDateTime.of(date, time),
        priority = priority
    )


sealed interface AddTaskUiEvent {
    data object Send : AddTaskUiEvent
    data object PriorityClick : AddTaskUiEvent
    data object TagClick : AddTaskUiEvent
    data object DateClick : AddTaskUiEvent
    data object DialogDismissed : AddTaskUiEvent
}


sealed class AddTaskDialogs {
    data object Priority : AddTaskDialogs()
    data object Tag : AddTaskDialogs()
    data object Date : AddTaskDialogs()
}

class AddTaskViewModel : ViewModel() {
    private val _task = MutableStateFlow(
        AddTaskState(
            date = LocalDate.now(),
            time = LocalTime.now()
        )
    )

    val visibleDialog = MutableStateFlow<AddTaskDialogs?>(null)
    val task: StateFlow<AddTaskState> = _task.asStateFlow()

    private suspend fun setIsValidated(isValidated: Boolean){
        coroutineScope { _task.update { it.copy(isValidated = isValidated) } }
    }

    fun setTitle(title: String) {
        viewModelScope.launch {
            _task.update { it.copy(title = title) }
            setIsValidated(false)
        }
    }

    fun setDescription(description: String) {
        viewModelScope.launch {
            _task.update { it.copy(description = description) }
            setIsValidated(false)
        }
    }

    fun setPriority(priority: Int?) {
        viewModelScope.launch {
            _task.update { it.copy(priority = priority) }
            setIsValidated(false)
        }
    }

    fun setDate(date: LocalDate?) {
        viewModelScope.launch {
            _task.update { it.copy(date = date) }
            setIsValidated(false)
        }
    }


    fun setDate(timestamp: Long?) {
        viewModelScope.launch {
            val date = timestamp?.toLocalDate()
            _task.update { it.copy(date = date) }
            setIsValidated(false)
        }
    }

    fun setTime(hour: Int, minute: Int) {
        viewModelScope.launch {
            val time = LocalTime.of(hour, minute)
            _task.update { it.copy(time = time) }
            setIsValidated(false)
        }
    }

    fun setDateAndTime(dateMillis: Long?, time: LocalTime?) {
        viewModelScope.launch {
            _task.update {
                it.copy(
                    time = time,
                    date = dateMillis?.toLocalDate()
                )
            }

            setIsValidated(false)
        }
    }


    fun setTime(time: LocalTime?) {
        viewModelScope.launch {
            _task.update { it.copy(time = time) }
            setIsValidated(false)
        }
    }

    fun updateTask(block: AddTaskState.() -> AddTaskState) {
        viewModelScope.launch {
            _task.update { currentState ->
                currentState.block()
            }
            setIsValidated(false)
        }
    }

    fun validate(field: ValidationField? = null) {
        viewModelScope.launch {
            when (field) {
                ValidationField.TITLE ->
                    validateField(ValidationField.DESCRIPTION, ::validateDate) { errors ->
                        this.copy(titleErrors = errors)
                    }


                ValidationField.DESCRIPTION ->
                    validateField(ValidationField.DESCRIPTION, ::validateDate) { errors ->
                        this.copy(descriptionErrors = errors)
                    }


                ValidationField.DATE -> {}
                ValidationField.TIME -> {}
                ValidationField.PRIORITY -> {}
                null -> {
                    _task.update {
                        val dateValidationResult = validateDate(it.date)
                        val titleValidationResult = validateTitle(it.title)
                        val timeValidationResult = validateTime(it.time)

                        it.copy(
                            dateErrors = dateValidationResult.errors?.get(ValidationField.DATE),
                            titleErrors = titleValidationResult.errors?.get(ValidationField.TITLE),
                            timeErrors = timeValidationResult.errors?.get(ValidationField.TIME),
                        )
                    }
                }
            }

            setIsValidated(true)
        }
    }


    private inline fun <reified T> validateField(
        field: ValidationField,
        validationFunction: (T) -> ValidationResult,
        updateErrors: AddTaskState.(List<String>?) -> AddTaskState,
    ) {
        val currentValue: T = when (field) {
            ValidationField.TITLE -> _task.value.title as T
            ValidationField.DESCRIPTION -> task.value.description as T
            ValidationField.DATE -> task.value.date as T
            ValidationField.TIME -> task.value.time as T
            ValidationField.PRIORITY -> task.value.priority as T
        }

        val res = validationFunction(currentValue)
        _task.update { it.updateErrors(res.errors?.get(field)) }
    }

    fun onEvent(event: AddTaskUiEvent) {
        when (event) {
            AddTaskUiEvent.Send -> {
                viewModelScope.launch {
                    validate()
                }
            }

            AddTaskUiEvent.DateClick -> {
                visibleDialog.update { AddTaskDialogs.Date }
            }

            AddTaskUiEvent.PriorityClick -> {
                visibleDialog.update { AddTaskDialogs.Priority }
            }

            AddTaskUiEvent.TagClick -> {
                visibleDialog.update { AddTaskDialogs.Tag }
            }

            AddTaskUiEvent.DialogDismissed -> {
                visibleDialog.update { null }
            }
        }
    }


}