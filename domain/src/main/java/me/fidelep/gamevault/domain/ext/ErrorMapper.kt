package me.fidelep.gamevault.domain.ext

import me.fidelep.gamevault.domain.model.ResponseError
import me.fidelep.gamevault.domain.model.VideoGameResult
import java.io.IOException
import java.net.UnknownHostException

fun Exception.mapError(): VideoGameResult {
    val error =
        when (this) {
            is UnknownHostException -> ResponseError.NO_INTERNET
            is IOException -> ResponseError.WRITING_READING
            else -> ResponseError.GENERIC
        }

    return VideoGameResult.Error(error)
}
