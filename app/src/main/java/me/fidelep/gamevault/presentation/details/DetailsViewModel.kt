package me.fidelep.gamevault.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.fidelep.gamevault.domain.model.VideoGameModel
import me.fidelep.gamevault.domain.model.VideoGameResult
import me.fidelep.gamevault.domain.usecase.RemoveVideoGameUseCase
import me.fidelep.gamevault.domain.usecase.UpdateVideoGameUseCase
import me.fidelep.gamevault.presentation.state.UiState
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val updateVideoGameUseCase: UpdateVideoGameUseCase,
    private val removeVideoGameUseCase: RemoveVideoGameUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun updateVideoGame(videoGameModel: VideoGameModel) {
        // TODO: Copy videogame and changed modified detials
        _uiState.update {
            it.copy(
                isLoading = true,
            )
        }
        viewModelScope.launch {
            val result = updateVideoGameUseCase(videoGameModel)

            when (result) {
                is VideoGameResult.Error ->
                    _uiState.update {
                        UiState(isLoading = false, error = result.value)
                    }

                is VideoGameResult.Success<*> ->
                    _uiState.update {
                        UiState(isLoading = false, videoGames = result.data as List<VideoGameModel>)
                    }
            }
        }
    }

    fun removeVideoGame(videoGameModel: VideoGameModel) {
        _uiState.update {
            it.copy(
                isLoading = true,
            )
        }
        viewModelScope.launch {
            val result = removeVideoGameUseCase(videoGameModel)

            when (result) {
                is VideoGameResult.Error ->
                    _uiState.update {
                        UiState(isLoading = false, error = result.value)
                    }

                is VideoGameResult.Success<*> ->
                    _uiState.update {
                        UiState(isLoading = false, videoGames = result.data as List<VideoGameModel>)
                    }
            }
        }
    }
}
