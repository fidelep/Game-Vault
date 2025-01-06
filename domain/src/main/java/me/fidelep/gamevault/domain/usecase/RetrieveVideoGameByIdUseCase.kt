package me.fidelep.gamevault.domain.usecase

import me.fidelep.gamevault.domain.interfaces.ILocalRepository
import me.fidelep.gamevault.domain.interfaces.IParamUseCase
import me.fidelep.gamevault.domain.model.VideoGameResult

class RetrieveVideoGameByIdUseCase(
    private val localRepository: ILocalRepository,
) : IParamUseCase<Int, VideoGameResult> {
    override suspend fun invoke(param: Int): VideoGameResult = localRepository.getVideoGamesById(param)
}
