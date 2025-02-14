package com.tba.todox.feature.home.add_task

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import java.time.LocalTime


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDateTime(
    dateState: DatePickerState,
    timeState: TimePickerState,
    onDismissRequest: () -> Unit,
    onSave: () -> Unit,
) {

    var isEditingDate by remember { mutableStateOf(true) }

    BackHandler {
        if (isEditingDate) return@BackHandler onDismissRequest()
        isEditingDate = true
    }

    Dialog(onDismissRequest) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            AnimatedVisibility(
                visible = isEditingDate,
                enter = slideInHorizontally(initialOffsetX = { fullWidth -> -fullWidth * 3 }) + fadeIn(),
                exit = slideOutHorizontally(targetOffsetX = { fullWidth -> -fullWidth * 3 }) + fadeOut(),
                label = "DatePicker"
            ) {
                AddTaskDatePicker(state = dateState, onNext = { isEditingDate = false })
            }
            AnimatedVisibility(
                visible = !isEditingDate,
                enter = slideInHorizontally(initialOffsetX = { fullWidth -> fullWidth * 3 }) + fadeIn(),
                exit = slideOutHorizontally(targetOffsetX = { fullWidth -> fullWidth * 3 }) + fadeOut(),
                label = "DatePicker"
            ) {
                AddTaskTimePicker(
                    state = timeState,
                    onCancel = { isEditingDate = true },
                    onNext = onSave
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
fun TimePickerState.getLocalTime(): LocalTime =
    run { LocalTime.of(hour, minute) }
