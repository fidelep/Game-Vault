package me.fidelep.gamevault.domain.usecase

import kotlinx.coroutines.test.runTest
import me.fidelep.gamevault.domain.TestConstants.mockedVideoGame
import me.fidelep.gamevault.domain.TestConstants.mockedVideoGameList
import me.fidelep.gamevault.domain.interfaces.ILocalRepository
import me.fidelep.gamevault.domain.model.SearchFilter
import me.fidelep.gamevault.domain.model.SearchModel
import me.fidelep.gamevault.domain.model.VideoGameResult
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.Mockito.`when` as mockitoWhen

@RunWith(MockitoJUnitRunner::class)
class GetVideoGamesUseCaseTest {
    @Mock
    private lateinit var localRepository: ILocalRepository

    private lateinit var useCase: GetVideoGamesUseCase

    @Before
    fun setUp() {
        useCase = GetVideoGamesUseCase(localRepository)
    }

    @Test
    fun `should return a list of VideoGameModel when calls getVideoGamesByCoincidence with SearchFilter NAME`() =
        runTest {
            val name = mockedVideoGame().title
            val searchModel = SearchModel(searchFilter = SearchFilter.NAME, searchParams = name)
            val expected =
                VideoGameResult.Success(mockedVideoGameList().filter { it.title == name })
            mockitoWhen(localRepository.getVideoGamesByCoincidence(name)).thenReturn(expected)

            val result = useCase(searchModel)

            verify(localRepository).getVideoGamesByCoincidence(name)
            assertEquals(expected, result)
        }

    @Test
    fun `should return a list of VideoGameModel when calls getVideoGamesByGenre with SearchFilter GENRE`() =
        runTest {
            val genre = mockedVideoGame().genre
            val searchModel = SearchModel(searchFilter = SearchFilter.GENRE, searchParams = genre)
            val expected =
                VideoGameResult.Success(mockedVideoGameList().filter { it.genre == genre })
            mockitoWhen(localRepository.getVideoGamesByGenre(genre)).thenReturn(expected)

            val result = useCase(searchModel)

            verify(localRepository).getVideoGamesByGenre(genre)
            assertEquals(expected, result)
        }

    @Test
    fun `should return a list of VideoGameModel when calls getAllVideoGames with no specific params`() =
        runTest {
            val searchModel = SearchModel()
            val expected = VideoGameResult.Success(mockedVideoGameList())
            mockitoWhen(localRepository.getAllVideoGames()).thenReturn(expected)

            val result = useCase(searchModel)

            verify(localRepository).getAllVideoGames()
            assertEquals(expected, result)
        }

    @Test
    fun `should return a list of VideoGameModel when calls getAllVideoGames with no specific SearchFilter ALL`() =
        runTest {
            val searchModel =
                SearchModel(searchFilter = SearchFilter.ALL)
            val expected = VideoGameResult.Success(mockedVideoGameList())
            mockitoWhen(localRepository.getAllVideoGames()).thenReturn(expected)

            val result = useCase(searchModel)

            verify(localRepository).getAllVideoGames()
            assertEquals(expected, result)
        }
}
