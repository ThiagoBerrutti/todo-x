package com.tba.todox.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tba.todox.R

@Composable
fun HomeEmptyScreen(
    modifier: Modifier = Modifier,


) {
    Column(
        modifier = modifier.fillMaxSize().padding(74.dp,74.dp,74.dp),
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
