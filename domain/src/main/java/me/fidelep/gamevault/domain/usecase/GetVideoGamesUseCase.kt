package me.fidelep.gamevault.domain.usecase

import me.fidelep.gamevault.domain.interfaces.ILocalRepository
import me.fidelep.gamevault.domain.interfaces.IParamUseCase
import me.fidelep.gamevault.domain.model.SearchFilter
import me.fidelep.gamevault.domain.model.SearchModel
import me.fidelep.gamevault.domain.model.VideoGameResult

class GetVideoGamesUseCase(
    private val localRepository: ILocalRepository,
) : IParamUseCase<SearchModel, VideoGameResult> {
    override suspend fun invoke(param: SearchModel): VideoGameResult =

        when (param.searchFilter) {
            SearchFilter.NAME -> localRepository.getVideoGamesByCoincidence(param.searchParams)
            SearchFilter.GENRE -> localRepository.getVideoGamesByGenre(param.searchParams)
            else -> localRepository.getAllVideoGames()
        }
}
