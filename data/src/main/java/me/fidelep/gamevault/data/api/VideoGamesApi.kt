package me.fidelep.gamevault.data.api

import me.fidelep.gamevault.data.api.model.VideoGameDTO
import retrofit2.http.GET

interface VideoGamesApi {
    @GET("games")
    suspend fun getGames(): List<VideoGameDTO>?

    // TODO: if required we might add Api endpoint to get details from online source
}
