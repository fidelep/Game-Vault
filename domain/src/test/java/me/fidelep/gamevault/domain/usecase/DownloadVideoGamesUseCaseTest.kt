package me.fidelep.gamevault.domain.usecase

import kotlinx.coroutines.test.runTest
import me.fidelep.gamevault.domain.TestConstants.mockedVideoGameList
import me.fidelep.gamevault.domain.interfaces.IRemoteRepository
import me.fidelep.gamevault.domain.model.ResponseError
import me.fidelep.gamevault.domain.model.SearchModel
import me.fidelep.gamevault.domain.model.VideoGameResult
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.Mockito.`when` as mockitoWhen

@Suppress("UNCHECKED_CAST")
@RunWith(MockitoJUnitRunner::class)
class DownloadVideoGamesUseCaseTest {
    @Mock
    private lateinit var remoteRepository: IRemoteRepository

    @Mock
    private lateinit var saveVideoGamesUseCase: SaveVideoGamesUseCase

    @Mock
    private lateinit var getVideoGamesUseCase: GetVideoGamesUseCase

    private lateinit var downloadVideoGamesUseCase: DownloadVideoGamesUseCase

    @Before
    fun setUp() {
        downloadVideoGamesUseCase =
            DownloadVideoGamesUseCase(remoteRepository, saveVideoGamesUseCase, getVideoGamesUseCase)
    }

    @Test
    fun `should return VideoGameResult Success with list when invokes  DownloadVideoGamesUseCase`() =
        runTest {
            val expected = VideoGameResult.Success(mockedVideoGameList())
            mockitoWhen(remoteRepository.downloadVideoGames()).thenReturn(expected)
            mockitoWhen(getVideoGamesUseCase(SearchModel())).thenReturn(expected)

            val result = downloadVideoGamesUseCase()

            assertEquals(expected, result)
            assertTrue(result is VideoGameResult.Success<*>)
            assertTrue((result as VideoGameResult.Success<List<*>>).data.isNotEmpty())
            verify(remoteRepository).downloadVideoGames()
        }

    @Test
    fun `should invoke SaveVideoGamesUseCase when returns VideoGameResult Success while invokes  DownloadVideoGamesUseCase`() =
        runTest {
            val expected = mockedVideoGameList()

            mockitoWhen(remoteRepository.downloadVideoGames()).thenReturn(
                VideoGameResult.Success(
                    mockedVideoGameList(),
                ),
            )

            downloadVideoGamesUseCase()

            verify(saveVideoGamesUseCase).invoke(expected)
        }

    @Test
    fun `should invoke GetVideoGamesUseCase when returns VideoGameResult Success while invokes  DownloadVideoGamesUseCase`() =
        runTest {
            mockitoWhen(remoteRepository.downloadVideoGames()).thenReturn(
                VideoGameResult.Success(
                    mockedVideoGameList(),
                ),
            )

            downloadVideoGamesUseCase()

            verify(getVideoGamesUseCase).invoke(SearchModel())
        }

    @Test
    fun `should return ResponseError GENERIC when repository thros error`() =
        runTest {
            val expected = VideoGameResult.Error(ResponseError.GENERIC)

            mockitoWhen(remoteRepository.downloadVideoGames()).thenReturn(expected)
            val result = downloadVideoGamesUseCase()

            assertTrue(result is VideoGameResult.Error)
            verifyNoInteractions(saveVideoGamesUseCase, getVideoGamesUseCase)
        }
}
