package me.fidelep.gamevault.presentation.state

import me.fidelep.gamevault.domain.model.ResponseError
import me.fidelep.gamevault.domain.model.VideoGameModel

data class UiState(
    val isLoading: Boolean = false,
    val videoGames: List<VideoGameModel> = emptyList(),
    val error: ResponseError? = null,
)
