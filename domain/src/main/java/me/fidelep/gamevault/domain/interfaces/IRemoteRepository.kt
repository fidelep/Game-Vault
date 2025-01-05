package me.fidelep.gamevault.domain.interfaces

import me.fidelep.gamevault.domain.model.VideoGameResult

interface IRemoteRepository {
    suspend fun downloadVideoGames(): VideoGameResult

    // TODO: if required we might add Api endpoint to get details from online source
}
