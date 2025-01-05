package me.fidelep.gamevault.data.ext

import me.fidelep.gamevault.data.api.model.VideoGameDTO
import me.fidelep.gamevault.data.db.model.VideoGameEntity
import me.fidelep.gamevault.domain.model.VideoGameModel

fun VideoGameDTO.toModel() =
    VideoGameModel(
        id = id,
        title = title,
        thumbnail = thumbnail,
        shortDescription = shortDescription,
        gameUrl = gameUrl,
        genre = genre,
        platform = platform,
        publisher = publisher,
        developer = developer,
        releaseDate = releaseDate,
        freetogameProfileUrl = freetogameProfileUrl,
    )

fun VideoGameEntity.toModel() =
    VideoGameModel(
        id = id,
        title = title,
        thumbnail = thumbnail,
        shortDescription = shortDescription,
        gameUrl = gameUrl,
        genre = genre,
        platform = platform,
        publisher = publisher,
        developer = developer,
        releaseDate = releaseDate,
        freetogameProfileUrl = freetogameProfileUrl,
    )

fun VideoGameEntity.Companion.fromModel(
    videoGameModel: VideoGameModel,
    isActive: Boolean = true,
) = VideoGameEntity(
    id = videoGameModel.id,
    title = videoGameModel.title,
    thumbnail = videoGameModel.thumbnail,
    shortDescription = videoGameModel.shortDescription,
    gameUrl = videoGameModel.gameUrl,
    genre = videoGameModel.genre,
    platform = videoGameModel.platform,
    publisher = videoGameModel.publisher,
    developer = videoGameModel.developer,
    releaseDate = videoGameModel.releaseDate,
    freetogameProfileUrl = videoGameModel.freetogameProfileUrl,
    isActive = isActive,
)
