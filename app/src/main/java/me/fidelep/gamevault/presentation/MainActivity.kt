package me.fidelep.gamevault.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import me.fidelep.gamevault.R
import me.fidelep.gamevault.presentation.details.DetailsScreen
import me.fidelep.gamevault.presentation.details.DetailsViewModel
import me.fidelep.gamevault.presentation.splash.SplashScreen
import me.fidelep.gamevault.presentation.splash.SplashViewModel
import me.fidelep.gamevault.presentation.vault.VaultScreen
import me.fidelep.gamevault.ui.navigation.ScreenDestinations
import me.fidelep.gamevault.ui.state.UiEvent
import me.fidelep.gamevault.ui.theme.GameVaultTheme

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GameVaultTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(modifier = Modifier.padding(innerPadding)) {
                        val navController = rememberNavController()

                        NavHost(
                            navController = navController,
                            startDestination = ScreenDestinations.Splash.route,
                        ) {
                            getSplashScreen(navController)

                            getVaultScreen(navController)

                            getDetailRoute(navController)
                        }
                    }
                }
            }
        }
    }

    private fun NavGraphBuilder.getSplashScreen(navController: NavHostController) {
        composable(ScreenDestinations.Splash.route) {
            val viewModel: SplashViewModel by viewModels()

            val uiEvent = viewModel.uiEvent.collectAsState()
            val isLoading = uiEvent.value is UiEvent.Loading

            Log.d("SplashScreen", "${uiEvent.value}")

            if (uiEvent.value is UiEvent.Error) {
                ShowError(message = (uiEvent.value as UiEvent.Error).message) { finish() }
            }

            SplashScreen(
                navController = navController,
                isLoading = isLoading,
            )
        }
    }

    private fun NavGraphBuilder.getVaultScreen(navController: NavHostController) {
        composable(ScreenDestinations.Vault.route) {
            val videoGames = sharedViewModel.uiState.collectAsState()
            val uiEvent = sharedViewModel.uiEvent.collectAsState()

            Log.d("VaultScreen", "${uiEvent.value}")

            when (uiEvent.value) {
                is UiEvent.Error -> ShowError(message = (uiEvent.value as UiEvent.Error).message) { }

                is UiEvent.Message ->
                    ShowDialog(
                        title = stringResource(R.string.attention),
                        message = (uiEvent.value as UiEvent.Message).value,
                        icon = Icons.Filled.Info,
                    ) {}

                UiEvent.Loading -> ShowLoader(message = getString(R.string.loading))

                else -> {}
            }

            VaultScreen(
                navController = navController,
                onQueryChange = { query, filter ->
                    sharedViewModel.filterVideoGames(
                        query,
                        filter,
                    )
                },
                videoGames = videoGames.value,
            )
        }
    }

    private fun NavGraphBuilder.getDetailRoute(navController: NavHostController) {
        composable(
            ScreenDestinations.Details.route,
            arguments =
                listOf(
                    navArgument("video_game_id") {
                        type = NavType.IntType
                    },
                ),
        ) {
            val viewModel: DetailsViewModel by viewModels()

            val videoGameState = viewModel.videoGame.collectAsState(null)
            val uiEvent = viewModel.uiEvent.collectAsState()

            val param = it.arguments?.getInt("video_game_id")
            viewModel.retrieveVideoGame(param)

            when (uiEvent.value) {
                is UiEvent.Error ->
                    ShowError(message = (uiEvent.value as UiEvent.Error).message) {
                        navController.popBackStack()
                    }

                is UiEvent.Message ->
                    ShowDialog(
                        title = stringResource(R.string.attention),
                        message = (uiEvent.value as UiEvent.Message).value,
                        icon = Icons.Filled.Info,
                    ) {
                        sharedViewModel.getVideoGames()
                    }

                UiEvent.Loading -> ShowLoader(message = getString(R.string.loading))
                UiEvent.EndFlow -> onBackPressedDispatcher.onBackPressed()
                UiEvent.Idle -> sharedViewModel.getVideoGames()
            }

            videoGameState.value?.let { videoGame ->
                DetailsScreen(
                    videoGame = videoGame,
                    onDeleteVideoGame = { modifiedVideoGame ->
                        viewModel.removeVideoGame(
                            modifiedVideoGame,
                        )
                    },
                    onUpdateVideoGame = { modifiedVideoGame ->
                        viewModel.updateVideoGame(
                            modifiedVideoGame,
                        )
                    },
                )
            }
        }
    }
}
