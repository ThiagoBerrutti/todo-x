package com.tba.todox.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    val vm = koinInject<HomeViewModel>()
    val text = vm.test()
    val uiState by vm.uiState.collectAsState()

    HomeEmptyScreen()


}