package me.fidelep.gamevault.domain.usecase

import me.fidelep.gamevault.domain.interfaces.ILocalRepository
import me.fidelep.gamevault.domain.interfaces.IParamUseCase
import me.fidelep.gamevault.domain.model.VideoGameModel
import me.fidelep.gamevault.domain.model.VideoGameResult

class RemoveVideoGameUseCase(
    private val localRepository: ILocalRepository,
) : IParamUseCase<VideoGameModel, VideoGameResult> {
    override suspend fun invoke(param: VideoGameModel): VideoGameResult = localRepository.removeVideoGame(param)
}
