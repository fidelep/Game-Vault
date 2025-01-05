package me.fidelep.gamevault.domain

import me.fidelep.gamevault.domain.model.VideoGameModel

object TestConstants {
    fun mockedVideoGame() =
        VideoGameModel(
            id = 582,
            title = "Tarisland",
            thumbnail = "https://www.freetogame.com/g/582/thumbnail.jpg",
            shortDescription = "A cross-platform MMORPG developed by Level Infinite and Published by Tencent.",
            gameUrl = "https://www.freetogame.com/open/tarisland",
            genre = "MMORPG",
            platform = "PC (Windows)",
            publisher = "Tencent",
            developer = "Level Infinite",
            releaseDate = "2024-06-22",
            freetogameProfileUrl = "https://www.freetogame.com/tarisland",
        )

    @Suppress("ktlint:standard:max-line-length")
    fun mockedVideoGameList() =
        listOf(
            VideoGameModel(
                id = 582,
                title = "Tarisland",
                thumbnail = "https://www.freetogame.com/g/582/thumbnail.jpg",
                shortDescription = "A cross-platform MMORPG developed by Level Infinite and Published by Tencent.",
                gameUrl = "https://www.freetogame.com/open/tarisland",
                genre = "MMORPG",
                platform = "PC (Windows)",
                publisher = "Tencent",
                developer = "Level Infinite",
                releaseDate = "2024-06-22",
                freetogameProfileUrl = "https://www.freetogame.com/tarisland",
            ),
            VideoGameModel(
                id = 540,
                title = "Overwatch 2",
                thumbnail = "https://www.freetogame.com/g/540/thumbnail.jpg",
                shortDescription = "A hero-focused first-person team shooter from Blizzard Entertainment.",
                gameUrl = "https://www.freetogame.com/open/overwatch-2",
                genre = "Shooter",
                platform = "PC (Windows)",
                publisher = "Activision Blizzard",
                developer = "Blizzard Entertainment",
                releaseDate = "2022-10-04",
                freetogameProfileUrl = "https://www.freetogame.com/overwatch-2",
            ),
            VideoGameModel(
                id = 516,
                title = "PUBG: BATTLEGROUNDS",
                thumbnail = "https://www.freetogame.com/g/516/thumbnail.jpg",
                shortDescription = "Get into the action in one of the longest running battle royale games PUBG Battlegrounds.",
                gameUrl = "https://www.freetogame.com/open/pubg",
                genre = "Shooter",
                platform = "PC (Windows)",
                publisher = "KRAFTON, Inc.",
                developer = "KRAFTON, Inc.",
                releaseDate = "2022-01-12",
                freetogameProfileUrl = "https://www.freetogame.com/pubg",
            ),
            VideoGameModel(
                id = 508,
                title = "Enlisted",
                thumbnail = "https://www.freetogame.com/g/508/thumbnail.jpg",
                shortDescription = "Get ready to command your own World War II military squad in Gaijin and Darkflow Software’s MMO squad-based shooter Enlisted.",
                gameUrl = "https://www.freetogame.com/open/enlisted",
                genre = "Shooter",
                platform = "PC (Windows)",
                publisher = "Gaijin Entertainment",
                developer = "Darkflow Software",
                releaseDate = "2021-04-08",
                freetogameProfileUrl = "https://www.freetogame.com/enlisted",
            ),
            VideoGameModel(
                id = 345,
                title = "Forge of Empires",
                thumbnail = "https://www.freetogame.com/g/345/thumbnail.jpg",
                shortDescription = "A free to play 2D browser-based online strategy game, become the leader and raise your city.",
                gameUrl = "https://www.freetogame.com/open/forge-of-empires",
                genre = "Strategy",
                platform = "Web Browser",
                publisher = "InnoGames",
                developer = "InnoGames",
                releaseDate = "2012-04-17",
                freetogameProfileUrl = "https://www.freetogame.com/forge-of-empires",
            ),
        )
}
