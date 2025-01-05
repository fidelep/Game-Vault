package me.fidelep.gamevault.presentation.vault

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.fidelep.gamevault.domain.model.SearchFilter
import me.fidelep.gamevault.domain.model.SearchModel
import me.fidelep.gamevault.domain.model.VideoGameModel
import me.fidelep.gamevault.domain.model.VideoGameResult
import me.fidelep.gamevault.domain.usecase.GetVideoGamesUseCase
import me.fidelep.gamevault.presentation.state.UiState
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
@HiltViewModel
class VaultViewModel @Inject constructor(
    private val getVideoGamesUseCase: GetVideoGamesUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val result = getVideoGamesUseCase(SearchModel())

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

    fun filterVideoGames(
        params: String,
        filter: SearchFilter,
    ) {
        _uiState.update {
            it.copy(
                isLoading = true,
            )
        }
        viewModelScope.launch {
            val search = SearchModel(params, filter)

            val result = getVideoGamesUseCase(search)

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
