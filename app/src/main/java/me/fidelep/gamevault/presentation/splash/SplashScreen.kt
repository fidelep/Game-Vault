package me.fidelep.gamevault.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import me.fidelep.gamevault.R
import me.fidelep.gamevault.ui.navigation.ScreenDestinations
import me.fidelep.gamevault.ui.views.GameVaultLoader

@Composable
fun SplashScreen(
    navController: NavController,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
) {
    if (!isLoading) {
        navController.navigate(
            route =
                ScreenDestinations.Vault.route,
        ) {
            popUpTo(
                route =
                    ScreenDestinations.Splash.route,
            ) {
                inclusive = true
            }
        }
    }

    Box(
        modifier =
            modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors =
                            listOf(
                                MaterialTheme.colorScheme.background,
                                MaterialTheme.colorScheme.primary,
                            ),
                    ),
                ),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            GameVaultLoader(message = stringResource(R.string.loading_your_vault))
        }
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    SplashScreen(navController = rememberNavController(), isLoading = true)
}
