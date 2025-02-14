package com.tba.todox.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun HomeEmptyScreen(
    modifier: Modifier = Modifier,

) {
    Column(
        modifier = modifier.fillMaxSize().padding(vertical =74.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(painter = painterResource(id = R.drawable.checklist_rafiki_1), contentDescription = null)
            Text(
                text= "What do you want to do today?",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Tap + to add your tasks",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
