package me.fidelep.gamevault.domain.usecase

import me.fidelep.gamevault.domain.interfaces.IRemoteRepository
import me.fidelep.gamevault.domain.interfaces.IUseCase
import me.fidelep.gamevault.domain.model.SearchModel
import me.fidelep.gamevault.domain.model.VideoGameModel
import me.fidelep.gamevault.domain.model.VideoGameResult

class DownloadVideoGamesUseCase(
    private val remoteRepository: IRemoteRepository,
    private val saveVideoGamesUseCase: SaveVideoGamesUseCase,
    private val getVideoGamesUseCase: GetVideoGamesUseCase,
) : IUseCase<VideoGameResult> {
    @Suppress("UNCHECKED_CAST")
    override suspend fun invoke(): VideoGameResult {
        val result = remoteRepository.downloadVideoGames()

        return when (result) {
            is VideoGameResult.Error -> {
                return result
            }

            else -> {
                saveInLocal((result as VideoGameResult.Success<List<VideoGameModel>>).data)
                retrieveLocalCollection()
            }
        }
    }

    private suspend fun saveInLocal(videoGames: List<VideoGameModel>) {
        saveVideoGamesUseCase(videoGames)
    }

    private suspend fun retrieveLocalCollection(): VideoGameResult {
        val result = getVideoGamesUseCase(SearchModel())

        return result
    }
}
