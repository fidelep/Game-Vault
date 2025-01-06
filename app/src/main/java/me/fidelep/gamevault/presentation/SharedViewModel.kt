package me.fidelep.gamevault.presentation

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
import me.fidelep.gamevault.ui.state.UiEvent
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
@HiltViewModel
class SharedViewModel @Inject constructor(
    private val getVideoGamesUseCase: GetVideoGamesUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<List<VideoGameModel>>(listOf())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableStateFlow<UiEvent>(UiEvent.Loading)
    val uiEvent = _uiEvent.asStateFlow()

    init {
        getVideoGames()
    }

    fun getVideoGames() {
        viewModelScope.launch {
            _uiEvent.update { UiEvent.Loading }

            val result = getVideoGamesUseCase(SearchModel())

            when (result) {
                is VideoGameResult.Error ->
                    _uiEvent.update {
                        UiEvent.Error(result.value.toString()) // TODO: Implement friendly user error mapper
                    }

                is VideoGameResult.Success<*> -> {
                    _uiState.emit(result.data as List<VideoGameModel>)
                    _uiEvent.update { UiEvent.Idle }
                }
            }
        }
    }

    fun filterVideoGames(
        params: String,
        filter: SearchFilter,
    ) {
        viewModelScope.launch {
            _uiEvent.update { UiEvent.Loading }

            val search = SearchModel(params, filter)

            val result = getVideoGamesUseCase(search)

            when (result) {
                is VideoGameResult.Error ->
                    _uiEvent.update {
                        UiEvent.Error(result.value.toString()) // TODO: Implement friendly user error mapper
                    }

                is VideoGameResult.Success<*> -> {
                    _uiState.emit(result.data as List<VideoGameModel>)
                    _uiEvent.update { UiEvent.Idle }
                }
            }
        }
    }
}
