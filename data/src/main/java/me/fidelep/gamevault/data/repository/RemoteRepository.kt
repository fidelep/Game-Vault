package me.fidelep.gamevault.data.repository

import me.fidelep.gamevault.data.api.VideoGamesApi
import me.fidelep.gamevault.data.ext.toModel
import me.fidelep.gamevault.domain.ext.mapError
import me.fidelep.gamevault.domain.interfaces.IRemoteRepository
import me.fidelep.gamevault.domain.model.VideoGameResult

class RemoteRepository(
    private val videoGamesApi: VideoGamesApi,
) : IRemoteRepository {
    override suspend fun downloadVideoGames(): VideoGameResult =
        try {
            val result = videoGamesApi.getGames()?.map { it.toModel() }
            VideoGameResult.Success(result ?: listOf())
        } catch (exception: Exception) {
            exception.mapError()
        }
}
