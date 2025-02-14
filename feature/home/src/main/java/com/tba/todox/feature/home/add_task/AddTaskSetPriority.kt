package com.tba.todox.feature.home.add_task

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tba.todox.feature.home.R
import kotlinx.coroutines.launch
import kotlin.math.min


@Composable
fun AddTaskSetPriority(
    modifier: Modifier = Modifier,
    onPriorityChanged:(Int?)->Unit,
    onSave: (Int?) -> Unit,
) {
    var selected: Int? by remember { mutableStateOf(null) }

    LaunchedEffect(selected) {
        launch {
            onPriorityChanged(selected)
        }
    }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(Color(0xff363636))
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Task Priority", style = MaterialTheme.typography.titleSmall)

        Spacer(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .height(1.dp)
                .background(Color(0xff979797))
                .fillMaxWidth()
        )

        BoxWithConstraints(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ) {

            val itemSize = 64.dp
            val spacing = 16.dp
            val itemCount = 10

            val contentWidth = calculateRowWidth(itemCount, itemSize, spacing)

            LazyVerticalGrid(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .width(contentWidth)
                    .align(Alignment.Center),
                columns = GridCells.FixedSize(itemSize),
                verticalArrangement = Arrangement.spacedBy(spacing),
                horizontalArrangement = Arrangement.spacedBy(spacing)
            ) {
                items(itemCount, key = { it }) { index ->
                    val isSelected2 by remember() {
                        derivedStateOf {
                            index == selected
                        }
                    }

                    TaskPriorityItem(
                        value = index,
                        selected = isSelected2,
                        modifier = remember {
                            Modifier
                                .clickable {
                                    selected = if (isSelected2) null else index//
                                }
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedButton(
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                shape = RoundedCornerShape(4.dp),
                onClick = {},
                border = null
            ) { Text("Cancel") }

            Button(
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                shape = RoundedCornerShape(4.dp),
                onClick = { onSave(selected) }
            ) { Text("Save") }

        }
    }
}

// Está tendo uma verificacao de recomposicao no icone e no texto sempre que selected muda.
// Ele recompoe tudo e ainda n consegui resolver (não que faça diferença aqui)
@NonRestartableComposable
@Composable
fun TaskPriorityItem(
    value: Int,
    modifier: Modifier = Modifier,
    selected: Boolean,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .requiredSize(64.dp)
            .background(
                if (selected) MaterialTheme.colorScheme.primary else
                    Color(0xff272727)
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(ImageVector.vectorResource(R.drawable.flag), null)
        Spacer(Modifier.height(4.dp))
        Text("$value")
    }
}

fun BoxWithConstraintsScope.calculateRowWidth(
    itemCount: Int,
    itemSize: Dp,
    spacing: Dp = 0.dp,
): Dp {
    val maxItemsPerRow = ((maxWidth + spacing) / (itemSize + spacing)).toInt()
    val currentItemsPerRow = min(maxItemsPerRow, itemCount)

    return itemSize * currentItemsPerRow + spacing * (currentItemsPerRow - 1)
}
