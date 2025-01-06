package me.fidelep.gamevault.ui.navigation

sealed class ScreenDestinations(
    val route: String,
) {
    object Splash : ScreenDestinations("splash_route")

    object Vault : ScreenDestinations("vault_route")

    object Details : ScreenDestinations("details_route/{video_game_id}")
}
