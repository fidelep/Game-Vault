package me.fidelep.gamevault.domain.interfaces

import me.fidelep.gamevault.domain.model.VideoGameModel
import me.fidelep.gamevault.domain.model.VideoGameResult

interface ILocalRepository {
    suspend fun saveVideoGames(videogames: List<VideoGameModel>): VideoGameResult

    suspend fun removeVideoGame(videoGame: VideoGameModel): VideoGameResult

    suspend fun updateVideoGame(
        videoGame: VideoGameModel,
        isActive: Boolean = true,
    ): VideoGameResult

    suspend fun getAllVideoGames(): VideoGameResult

    suspend fun getVideoGamesByCoincidence(param: String): VideoGameResult

    suspend fun getVideoGamesByGenre(gender: String): VideoGameResult
}
