package com.tba.todox.ui

import android.util.Log
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tba.todox.R
import com.tba.todox.Task
import kotlinx.coroutines.launch

private sealed interface AddTasksModalOptions {
    data object Title : AddTasksModalOptions
    data object Description : AddTasksModalOptions
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAddTaskModalBottomSheet(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onSend:(Task) ->Unit
) {
    val titleFocusRequester = remember { FocusRequester() }
    val descriptionFocusRequester = remember { FocusRequester() }

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var focusedField: AddTasksModalOptions? by remember { mutableStateOf(AddTasksModalOptions.Title) }

    ModalBottomSheet(onDismissRequest) {
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
                value = title,
                singleLine = true,
                onValueChange = { title = it },
                placeholder = {
                    Text(
                        "Do math homework",
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
            )

            CustomTextField(
                value = description,
                onValueChange = { description = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(descriptionFocusRequester),
                colors = colors,
                placeholder = { Text("Description", style = MaterialTheme.typography.bodyLarge) },
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
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.clock),
                        contentDescription = null
                    )
                }

                IconButton({}) {
                    Icon(
                        painter = painterResource(id = R.drawable.tag),
                        contentDescription = null
                    )
                }
                IconButton({}) {
                    Icon(
                        painter = painterResource(id = R.drawable.flag),
                        contentDescription = null
                    )
                }
            }

            IconButton(
                modifier = Modifier.padding(end = 12.dp),
                onClick = {
                    onSend(Task(title,description))
                    onDismissRequest()

//                    focusedField = when (focusedField) {
//                        AddTasksModalOptions.Title -> {
//                            AddTasksModalOptions.Description
//                        }
//
//                        AddTasksModalOptions.Description -> {
//                            null
//                        }
//
//                        null -> {
//                            AddTasksModalOptions.Title
//                        }
//                    }
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.send_1),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null
                )

            }
        }
    }

    LaunchedEffect(true) {
        launch {
            titleFocusRequester.requestFocus()
            Log.d("TEST","ee")
        }
    }

}
