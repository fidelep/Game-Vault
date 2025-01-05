package me.fidelep.gamevault.data.repository

import me.fidelep.gamevault.data.db.dao.VideoGameDao
import me.fidelep.gamevault.data.db.model.VideoGameEntity
import me.fidelep.gamevault.data.ext.fromModel
import me.fidelep.gamevault.data.ext.toModel
import me.fidelep.gamevault.domain.ext.mapError
import me.fidelep.gamevault.domain.interfaces.ILocalRepository
import me.fidelep.gamevault.domain.model.ResponseError
import me.fidelep.gamevault.domain.model.VideoGameModel
import me.fidelep.gamevault.domain.model.VideoGameResult

class LocalRepository(
    private val videoGameDao: VideoGameDao,
) : ILocalRepository {
    override suspend fun saveVideoGames(videogames: List<VideoGameModel>): VideoGameResult =
        try {
            val result =
                videoGameDao.insert(
                    *videogames
                        .map { VideoGameEntity.fromModel(it) }
                        .toTypedArray(),
                )
            if (result.isEmpty()) {
                VideoGameResult.Error(ResponseError.NOT_SAVED)
            } else {
                VideoGameResult.Success(true)
            }
        } catch (exception: Exception) {
            exception.mapError()
        }

    override suspend fun removeVideoGame(videoGame: VideoGameModel): VideoGameResult = updateVideoGame(videoGame, false)

    override suspend fun updateVideoGame(
        videoGame: VideoGameModel,
        isActive: Boolean,
    ): VideoGameResult =
        try {
            val result = videoGameDao.update(VideoGameEntity.fromModel(videoGame, isActive))

            if (result == -1) {
                VideoGameResult.Error(ResponseError.CANT_MODIFY)
            } else {
                VideoGameResult.Success(result)
            }
        } catch (exception: Exception) {
            exception.mapError()
        }

    override suspend fun getAllVideoGames(): VideoGameResult =
        try {
            val result = videoGameDao.getAll().map { it.toModel() }
            VideoGameResult.Success(result)
        } catch (exception: Exception) {
            exception.mapError()
        }

    override suspend fun getVideoGamesByCoincidence(param: String): VideoGameResult =
        try {
            val result =
                videoGameDao
                    .getByTitle("%$param%") // TODO: Handle % symbol in another part
                    .map { it.toModel() }
            VideoGameResult.Success(result)
        } catch (exception: Exception) {
            exception.mapError()
        }

    override suspend fun getVideoGamesByGenre(gender: String): VideoGameResult =
        try {
            val result =
                videoGameDao
                    .getByGenre("%$gender%") // TODO: Handle % symbol in another part
                    .map { it.toModel() }
            VideoGameResult.Success(result)
        } catch (exception: Exception) {
            exception.mapError()
        }
}
