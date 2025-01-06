package me.fidelep.gamevault.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.fidelep.gamevault.domain.model.VideoGameResult
import me.fidelep.gamevault.domain.usecase.DownloadVideoGamesUseCase
import me.fidelep.gamevault.ui.state.UiEvent
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val downloadVideoGamesUseCase: DownloadVideoGamesUseCase,
) : ViewModel() {
    private val _uiEvent = MutableStateFlow<UiEvent>(UiEvent.Loading)
    val uiEvent = _uiEvent.asStateFlow()

    init {
        downloadVideoGames()
    }

    private fun downloadVideoGames() {
        _uiEvent.update {
            UiEvent.Loading
        }
        viewModelScope.launch {
            delay(1500L) // Just for aesthetics (user can enjoy the loading icon)

            val result = downloadVideoGamesUseCase()

            when (result) {
                is VideoGameResult.Error ->
                    _uiEvent.update {
                        UiEvent.Error(result.value.toString()) // TODO: Implement friendly user error mapper
                    }

                is VideoGameResult.Success<*> ->
                    _uiEvent.update {
                        UiEvent.Idle
                    }
            }
        }
    }
}
