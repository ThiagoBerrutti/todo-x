package com.tba.todox.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.google.android.material.search.SearchBar
import com.tba.todox.core.model.Tag
import com.tba.todox.core.model.Task
import org.koin.compose.koinInject
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    val vm = koinInject<HomeViewModel>()
    val uiState by vm.uiState.collectAsState()
    val onEvent: (HomeUiEvent) -> Unit = remember { vm::onEvent }

    when (val state = uiState) {
        is HomeUiState.Error -> {}
        HomeUiState.Loading -> {}
        is HomeUiState.Success -> {
            val tasks = remember(state.tasks) { state.tasks }

            if (tasks.isEmpty()) {
                HomeEmptyScreen()
            } else {
                Column(Modifier.padding(24.dp)) {

                    SearchBar()
                    Spacer(Modifier.height(20.dp))
                    TaskList(tasks, { onEvent(HomeUiEvent.ClickTask(it)) })
                }
            }
        }
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    OutlinedTextField(
        "" ,
        onValueChange = {},
        leadingIcon = { Icon(ImageVector.vectorResource(R.drawable.search_normal_1), null,
            modifier = Modifier.size(24.dp))},
        placeholder = { Text("Search for your task")},
        modifier = Modifier
            .border(0.8.dp, Color(0xff979797), RoundedCornerShape(4.dp))
            .fillMaxWidth()
            .background(Color(0xff1D1D1D))
            .clip(RoundedCornerShape(4.dp))
    )

}

@Composable
fun TaskList(
    tasks:List<Task>,
    onItemClick:(Int) ->Unit,
    modifier: Modifier = Modifier
) {
    Column(
        Modifier
            .fillMaxWidth()
            .then(modifier),
    ) {
        repeat(tasks.size){ i ->
            TaskListItem(
                task = tasks[i],
                modifier = Modifier
                    .background(Color(0xff363636))
                    .clickable { onItemClick(i) }
            )
            if (i < tasks.size) {
                Spacer(Modifier.size(16.dp))
            }
        }
    }
}

@Composable
fun TaskListItem(task: Task, modifier: Modifier = Modifier) {
    Row(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(4.dp))
            .then(
                modifier
                    .padding(4.dp)
            )
    ) {
        Column(
            modifier = Modifier.align(Alignment.CenterVertically),
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 6.dp, end = 12.dp)
                    .size(16.dp)
                    .border((1.5).dp, Color.White, CircleShape)
            )
        }
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = task.title + "12312312312312qweqweqweqweqweqweqweqweqwe",
                    style = LocalTextStyle.current.merge(
                        MaterialTheme.typography.bodyLarge.copy(
                            letterSpacing = -(0.32).sp,
                        )
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White
                )
                Text(
                    text = task.dateTime?.formatDate() ?: "",
                    style = LocalTextStyle.current.merge(
                        MaterialTheme.typography.bodyMedium.copy(
                            letterSpacing = (-0.32).sp
                        )
                    ),
                    color = Color(0xffAFAFAF),
                )
            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                val tag = Tag("Work", 0xffFF8080, R.drawable.tag)

                TaskListItemTag(tag)

                if (task.priority != null) {
                    TaskListItemPriority(task.priority!!)
                }

            }
        }

    }
    Row(modifier = modifier.fillMaxWidth()) {
    }
}

@Composable
fun TaskListItemTag(tag: Tag, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
//            .height(30.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(Color(tag.color))
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(tag.icon),
            contentDescription = null,
            modifier = Modifier
                .padding(vertical = 7.5.dp)
                .size(18.dp)
        )
        Text(
            text = tag.label,
            style = LocalTextStyle.current.merge(
                MaterialTheme.typography.bodySmall.copy(
                    letterSpacing = -(0.32).sp,
                    lineHeight = 1.5.em,
                    fontSize = 14.sp
//                fontSize = 40.sp


                )
            ),
            textAlign = TextAlign.Center,
//            modifier = Modifier.border(1.dp,Color.Black)
        )
    }
}

@Composable
fun TaskListItemPriority(priority: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(4.dp))
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.flag),
            contentDescription = null,
            modifier = Modifier
                .padding(vertical = 7.5.dp)
                .size(18.dp)
        )

        Text(
            text = "$priority",
            style = LocalTextStyle.current.merge(
                MaterialTheme.typography.bodySmall
                    .copy(
                        fontSize = 14.sp,
                    )
            )
        )

    }
}

private fun LocalDateTime.formatDate():String{
    val today = LocalDate.now()
    val tomorrow = today.plusDays(1)
    val yesterday = today.plusDays(-1)

    val day = when (this.toLocalDate()) {
        today -> "Today"
        yesterday -> "Yesterday"
        tomorrow -> "Tomorrow"
        else ->{format(DateTimeFormatter.ofPattern("dd/MM"))}
    }

    val time = this.toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm"))

    return "$day At $time"
}


