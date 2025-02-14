package com.tba.todox.feature.home.add_task

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import com.tba.todox.core.designsystem.components.CustomTextField
import com.tba.todox.feature.home.R
import com.tba.todox.feature.home.navigation.AddTaskRoute
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate
import java.time.LocalTime

fun NavGraphBuilder.addTask(onDismissRequest: () -> Unit) {
    dialog<AddTaskRoute> {
        AddTaskBottomSheet(onDismissRequest)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskBottomSheet(onDismissRequest: () -> Unit) {
    val vm = koinViewModel<AddTaskViewModel>()
    val task by vm.task.collectAsState()
    val visibleDialog by vm.visibleDialog.collectAsState()


    HomeAddTaskBottomSheet2(
        state = task,
        onTitleChanged = vm::setTitle,
        onDismissRequest = onDismissRequest,
        onBack = onDismissRequest,
        onSend = { vm.onEvent(AddTaskUiEvent.Send) },
        onDescriptionChanged = vm::setDescription,
        onTagClick = { vm.onEvent(AddTaskUiEvent.TagClick) },
        onDateClick = { vm.onEvent(AddTaskUiEvent.DateClick) },
        onPriorityClick = { vm.onEvent(AddTaskUiEvent.PriorityClick) },
    )


    val dismissDialog = remember { { vm.onEvent(AddTaskUiEvent.DialogDismissed) } }
    when (visibleDialog) {
        AddTaskDialogs.Date -> {

            val initialTime = task.time ?: LocalTime.now()
            val initialDate = task.date ?: LocalDate.now()
            val dateState =
                rememberDatePickerState(initialSelectedDateMillis = initialDate?.toTimestamp())
            val timeState =
                rememberTimePickerState(initialHour = initialTime.hour, initialTime.minute)

            AddDateTime(
                onDismissRequest = dismissDialog,
                dateState = dateState,
                timeState = timeState,
                onSave = {
                    vm.setDateAndTime(
                        dateState.selectedDateMillis,
                        timeState.getLocalTime()
                    )
                    dismissDialog()
                })
        }

        AddTaskDialogs.Priority -> {

        }

        AddTaskDialogs.Tag -> {
        }

        null -> {}
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAddTaskBottomSheet2(
    state: AddTaskState,
    modifier: Modifier = Modifier,
    onTitleChanged: (String) -> Unit,
    onDescriptionChanged: (String) -> Unit,
    onPriorityClick: () -> Unit,
    onTagClick: () -> Unit,
    onDateClick: () -> Unit,
    onDismissRequest: () -> Unit,
    onBack: () -> Unit,
    onSend: () -> Unit,
) {
    val titleFocusRequester = remember { FocusRequester() }
    val descriptionFocusRequester = remember { FocusRequester() }
    val scope = rememberCoroutineScope()

    var clickedOnSend by remember { mutableStateOf(false) }

    LaunchedEffect(state.isValidated) {
        if (state.isValidated && state.isValid() && clickedOnSend) {
            onDismissRequest()
            clickedOnSend = false
        }
    }

    BackHandler { onBack() }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        dragHandle = { Spacer(Modifier.height(24.dp)) }
    ) {
        Column(modifier = modifier.padding(start = 24.dp, end = 24.dp, bottom = 0.dp)) {
            Text("Add Task", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(14.dp))

            val colors = OutlinedTextFieldDefaults.colors().copy(
                focusedIndicatorColor = Color(0xFF979797),
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedPlaceholderColor = Color(0xFFAFAFAF),
                focusedPlaceholderColor = Color(0xFFAFAFAF)
            )

            CustomTextField(
                colors = colors,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp)
                    .focusRequester(titleFocusRequester),
                value = state.title,
                singleLine = true,
                onValueChange = onTitleChanged,
                placeholder = {
                    Text(
                        "Do math homework",
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
            )

            CustomTextField(
                value = state.description,
                onValueChange = onDescriptionChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(descriptionFocusRequester),
                colors = colors,
                placeholder = {
                    Text(
                        "Description",
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.padding(start = 12.dp, bottom = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(onClick = onDateClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.timer_1_outline),
                        contentDescription = null
                    )
                }

                IconButton(onClick = onTagClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.tag),
                        contentDescription = null
                    )
                }
                IconButton(onPriorityClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.flag),
                        contentDescription = null
                    )
                }
            }

            IconButton(
                modifier = Modifier.padding(end = 12.dp),
                onClick = {
                    scope.launch {
                        clickedOnSend = true
                        onSend()
                    }
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.send_1),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null
                )
            }
        }

        LaunchedEffect(Unit) {
            titleFocusRequester.requestFocus()
        }
    }
}
