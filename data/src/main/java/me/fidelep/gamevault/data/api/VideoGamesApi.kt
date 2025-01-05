package me.fidelep.gamevault.data.api

import me.fidelep.gamevault.data.api.model.VideoGameDTO
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface VideoGamesApi {
    @GET("games")
    suspend fun getGames(): List<VideoGameDTO>?

    // TODO: if required we might add Api endpoint to get details from online source

    companion object {
        @JvmStatic
        fun create(url: String): VideoGamesApi =
            Retrofit
                .Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(VideoGamesApi::class.java)
    }
}
