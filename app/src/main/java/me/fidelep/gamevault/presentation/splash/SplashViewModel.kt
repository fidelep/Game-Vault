package me.fidelep.gamevault.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.fidelep.gamevault.domain.model.VideoGameResult
import me.fidelep.gamevault.domain.usecase.DownloadVideoGamesUseCase
import me.fidelep.gamevault.presentation.state.UiState
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val downloadVideoGamesUseCase: DownloadVideoGamesUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        downdloadVideogames()
    }

    fun downdloadVideogames() {
        _uiState.update {
            it.copy(
                isLoading = true,
            )
        }
        viewModelScope.launch {
            val result = downloadVideoGamesUseCase()

            when (result) {
                is VideoGameResult.Error ->
                    _uiState.update {
                        UiState(isLoading = false, error = result.value)
                    }

                is VideoGameResult.Success<*> ->
                    _uiState.update {
                        UiState(isLoading = false)
                    }
            }
        }
    }
}
