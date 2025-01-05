package me.fidelep.gamevault.domain.model

/**
 * Wrapping responses to only provide clean data to UI
 */
sealed interface VideoGameResult {
    data class Success<T : Any>(
        val data: T,
    ) : VideoGameResult

    data class Error(
        val value: ResponseError,
    ) : VideoGameResult
}
