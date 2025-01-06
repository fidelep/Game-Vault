package me.fidelep.gamevault.ui.state

sealed interface UiEvent {
    object Idle : UiEvent

    object Loading : UiEvent

    data class Error(
        val message: String,
    ) : UiEvent

    data class Message(
        val value: String,
    ) : UiEvent

    object EndFlow : UiEvent
}
