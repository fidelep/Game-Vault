package me.fidelep.gamevault.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

interface IUiEventHandler {
    @Composable
    fun ShowLoader(message: String?)

    @Composable
    fun ShowError(
        message: String,
        onDismiss: () -> Unit,
    )

    @Composable
    fun ShowDialog(
        title: String,
        message: String,
        icon: ImageVector,
        onDismiss: () -> Unit,
    )
}
