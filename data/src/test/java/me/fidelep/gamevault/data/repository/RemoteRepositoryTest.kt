package me.fidelep.gamevault.data.repository

import kotlinx.coroutines.test.runTest
import me.fidelep.gamevault.data.TestConstants.mockedDtoList
import me.fidelep.gamevault.data.TestConstants.mockedModelList
import me.fidelep.gamevault.data.api.VideoGamesApi
import me.fidelep.gamevault.domain.model.ResponseError
import me.fidelep.gamevault.domain.model.VideoGameModel
import me.fidelep.gamevault.domain.model.VideoGameResult
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.net.UnknownHostException
import org.mockito.Mockito.`when` as mockitoWhen

@Suppress("UNCHECKED_CAST")
@RunWith(MockitoJUnitRunner::class)
class RemoteRepositoryTest {
    @Mock
    private lateinit var api: VideoGamesApi

    private lateinit var repository: RemoteRepository

    @Before
    fun setUp() {
        repository = RemoteRepository(api)
    }

    @Test
    fun `should return VideoGameResult Success containing elements when repository calls downloadVideoGames`() =
        runTest {
            val expected = VideoGameResult.Success(mockedModelList())

            mockitoWhen(api.getGames()).thenReturn(mockedDtoList())

            val result = repository.downloadVideoGames()

            assert(result is VideoGameResult.Success<*>)
            assertEquals(expected, result)
        }

    @Test
    fun `should return VideoGameResult Success empty when repository calls getVideoGamesByCoincidence and return empty`() =
        runTest {
            val expected = VideoGameResult.Success(emptyList<VideoGameModel>())

            mockitoWhen(api.getGames()).thenReturn(emptyList())

            val result = repository.downloadVideoGames()

            assert(result is VideoGameResult.Success<*>)
            assert((result as VideoGameResult.Success<List<VideoGameModel>>).data.isEmpty())
            assertEquals(expected, result)
        }

    @Test
    fun `should return VideoGameResult Error containing ResponseError GENERIC when dao throws an exception`() =
        runTest {
            val expected = VideoGameResult.Error(ResponseError.GENERIC)
            mockitoWhen(repository.downloadVideoGames()).thenThrow()

            val result = repository.downloadVideoGames()

            assert(result is VideoGameResult.Error)
            assertEquals(expected, result)
        }

    @Test
    fun `should return VideoGameResult Error containing ResponseError NO_INTERNET when dao throws UnknownHostException`() =
        runTest {
            val expected = VideoGameResult.Error(ResponseError.NO_INTERNET)
            mockitoWhen(repository.downloadVideoGames()).thenAnswer { throw UnknownHostException() }

            val result = repository.downloadVideoGames()

            assert(result is VideoGameResult.Error)
            assertEquals(expected, result)
        }
}
