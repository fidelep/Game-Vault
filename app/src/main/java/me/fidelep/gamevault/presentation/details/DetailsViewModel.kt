package me.fidelep.gamevault.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.fidelep.gamevault.domain.model.VideoGameModel
import me.fidelep.gamevault.domain.model.VideoGameResult
import me.fidelep.gamevault.domain.usecase.RemoveVideoGameUseCase
import me.fidelep.gamevault.domain.usecase.RetrieveVideoGameByIdUseCase
import me.fidelep.gamevault.domain.usecase.UpdateVideoGameUseCase
import me.fidelep.gamevault.ui.state.UiEvent
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val retrieveVideoGameByIdUseCase: RetrieveVideoGameByIdUseCase,
    private val updateVideoGameUseCase: UpdateVideoGameUseCase,
    private val removeVideoGameUseCase: RemoveVideoGameUseCase,
) : ViewModel() {
    private val _videoGame = MutableSharedFlow<VideoGameModel>()
    val videoGame = _videoGame.asSharedFlow()

    private val _uiEvent = MutableStateFlow<UiEvent>(UiEvent.Loading)
    val uiEvent = _uiEvent.asStateFlow()

    fun retrieveVideoGame(videoGameId: Int?) {
        viewModelScope.launch {
            _uiEvent.update { UiEvent.Loading }

            val result = retrieveVideoGameByIdUseCase(videoGameId ?: -1)

            when (result) {
                is VideoGameResult.Error -> onError(result)

                is VideoGameResult.Success<*> -> {
                    _uiEvent.update { UiEvent.Idle }
                    _videoGame.emit(result.data as VideoGameModel)
                }
            }
        }
    }

    fun updateVideoGame(videoGameModel: VideoGameModel) {
        viewModelScope.launch {
            _uiEvent.update { UiEvent.Loading }

            val result = updateVideoGameUseCase(videoGameModel)

            when (result) {
                is VideoGameResult.Error -> onError(result)

                is VideoGameResult.Success<*> -> {
                    _videoGame.emit(videoGameModel)
                    _uiEvent.update { UiEvent.Message("Se edito correctamente") } // TODO: extract string
                    _uiEvent.update { UiEvent.Idle }
                }
            }
        }
    }

    fun removeVideoGame(videoGameModel: VideoGameModel) {
        viewModelScope.launch {
            _uiEvent.update { UiEvent.Loading }

            val result = removeVideoGameUseCase(videoGameModel)

            when (result) {
                is VideoGameResult.Error -> onError(result)

                is VideoGameResult.Success<*> -> {
                    _uiEvent.update { UiEvent.Message("Se borro correctamente") } // TODO: extract string
                    _uiEvent.update { UiEvent.Idle }
                }
            }
        }
    }

    private fun onError(error: VideoGameResult.Error) {
        _uiEvent.update { UiEvent.Error(error.value.toString()) } // TODO: Implement friendly user error mapper
        _uiEvent.update { UiEvent.EndFlow }
    }
}
