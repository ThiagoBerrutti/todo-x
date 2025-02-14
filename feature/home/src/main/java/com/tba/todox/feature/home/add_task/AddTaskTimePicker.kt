package com.tba.todox.feature.home.add_task

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskTimePicker(
    modifier: Modifier = Modifier,
    state: TimePickerState,
    onTimeChanged: (Int, Int) -> Unit = { _, _ -> },
    onCancel: () -> Unit = {},
    onNext: () -> Unit,
) {
    LaunchedEffect(state.hour, state.minute) {
        onTimeChanged(state.hour, state.minute)
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TimePicker(state)
        Row(Modifier.fillMaxWidth()) {
            OutlinedButton(onClick = onCancel) { Text("Cancel") }
            Button(onClick = onNext) { Text("Next") }
        }
    }
}