package me.fidelep.gamevault.domain.usecase

import me.fidelep.gamevault.domain.interfaces.ILocalRepository
import me.fidelep.gamevault.domain.interfaces.IParamUseCase
import me.fidelep.gamevault.domain.model.VideoGameModel
import me.fidelep.gamevault.domain.model.VideoGameResult

class SaveVideoGamesUseCase(
    private val localRepository: ILocalRepository,
) : IParamUseCase<List<VideoGameModel>, VideoGameResult> {
    override suspend fun invoke(param: List<VideoGameModel>): VideoGameResult = localRepository.saveVideoGames(param)
}
