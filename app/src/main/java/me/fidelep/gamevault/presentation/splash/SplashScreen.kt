package me.fidelep.gamevault.presentation.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import me.fidelep.gamevault.R
import me.fidelep.gamevault.ui.navigation.ScreenDestinations

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
            AnimatedIcon()

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.loading_your_vault),
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun AnimatedIcon(modifier: Modifier = Modifier) {
    var currentRotation by remember { mutableFloatStateOf(0f) }

    val rotation = remember { Animatable(currentRotation) }

    LaunchedEffect(key1 = true) {
        rotation.animateTo(
            targetValue = currentRotation + 360f,
            animationSpec =
                infiniteRepeatable(
                    animation = tween(2000, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart,
                ),
        ) {
            currentRotation = value
        }
    }

    Icon(
        modifier =
            modifier
                .size(96.dp)
                .rotate(rotation.value),
        painter = painterResource(id = R.drawable.ic_videogame),
        contentDescription = stringResource(R.string.video_game_icon),
        tint = MaterialTheme.colorScheme.secondary,
    )
}

@Preview
@Composable
private fun SplashScreenPreview() {
    SplashScreen(navController = rememberNavController(), isLoading = true)
}