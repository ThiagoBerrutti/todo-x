package com.tba.todox

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tba.todox.MainActivityUiState.Loading
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MainActivityViewModel:ViewModel() {
    fun test() = "Testando"
    // Adicionar flows dos dados que precisarem ser carregados
    val uiState = MutableStateFlow(null).map { MainActivityUiState.Success}
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Loading
        )
}

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState

    data object Success: MainActivityUiState

    fun shouldKeepSplashScreen() = this is Loading
}
