package me.fidelep.gamevault.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import me.fidelep.gamevault.presentation.details.DetailsScreen
import me.fidelep.gamevault.presentation.details.DetailsViewModel
import me.fidelep.gamevault.presentation.splash.SplashScreen
import me.fidelep.gamevault.presentation.splash.SplashViewModel
import me.fidelep.gamevault.presentation.vault.VaultScreen
import me.fidelep.gamevault.presentation.vault.VaultViewModel
import me.fidelep.gamevault.ui.navigation.ScreenDestinations
import me.fidelep.gamevault.ui.theme.GameVaultTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
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
                            composable(ScreenDestinations.Splash.route) {
                                val viewModel: SplashViewModel by viewModels()
                                val uiState = viewModel.uiState.collectAsState()
                                Log.d("UI State", "isLoading: ${uiState.value.isLoading}")
                                SplashScreen(
                                    navController = navController,
                                    isLoading = uiState.value.isLoading,
                                )
                            }

                            composable(ScreenDestinations.Vault.route) {
                                val viewModel: VaultViewModel by viewModels()
                                val uiState = viewModel.uiState.collectAsState()
                                Log.d("UI State", "isLoading: ${uiState.value.videoGames}")

                                VaultScreen(
                                    navController = navController,
                                    onQueryChange = { query, filter ->
                                        viewModel.filterVideoGames(
                                            query,
                                            filter,
                                        )
                                    },
                                    videoGames = uiState.value.videoGames,
                                )
                            }

                            composable(ScreenDestinations.Details.route) {
                                val viewModel: DetailsViewModel by viewModels()

                                DetailsScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}
