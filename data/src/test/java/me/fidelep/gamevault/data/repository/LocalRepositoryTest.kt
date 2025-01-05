package me.fidelep.gamevault.data.repository

import kotlinx.coroutines.test.runTest
import me.fidelep.gamevault.data.TestConstants.mockedEntityList
import me.fidelep.gamevault.data.TestConstants.mockedModelList
import me.fidelep.gamevault.data.TestConstants.mockedVideoGame
import me.fidelep.gamevault.data.db.dao.VideoGameDao
import me.fidelep.gamevault.domain.model.ResponseError
import me.fidelep.gamevault.domain.model.VideoGameModel
import me.fidelep.gamevault.domain.model.VideoGameResult
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import java.io.IOException
import org.mockito.Mockito.`when` as mockitoWhen

@Suppress("UNCHECKED_CAST")
@RunWith(MockitoJUnitRunner::class)
class LocalRepositoryTest {
    @Mock
    private lateinit var dao: VideoGameDao

    private lateinit var repository: LocalRepository

    @Before
    fun setUp() {
        repository = LocalRepository(dao)
    }

    @Test
    fun `should return VideoGameResult Success containing elements when repository calls getAllVideoGames`() =
        runTest {
            val expected = VideoGameResult.Success(mockedModelList())

            mockitoWhen(dao.getAll()).thenReturn(mockedEntityList())

            val result = repository.getAllVideoGames()

            assert(result is VideoGameResult.Success<*>)
            assertEquals(expected, result)
        }

    @Test
    fun `should return VideoGameResult Success containing only active elements when repository calls getAllVideoGames`() =
        runTest {
            val expected = mockedEntityList().size
            mockitoWhen(dao.getAll()).thenReturn(mockedEntityList().filter { it.isActive })

            val result = repository.getAllVideoGames()

            assert(result is VideoGameResult.Success<*>)
            assertNotEquals(
                expected,
                (result as VideoGameResult.Success<List<VideoGameModel>>).data.size,
            )
        }

    @Test
    fun `should return VideoGameResult Success containing elements when repository calls getVideoGamesByCoincidence`() =
        runTest {
            val name = mockedVideoGame().title
            val expected = VideoGameResult.Success(mockedModelList().filter { it.title == name })

            mockitoWhen(dao.getByTitle(name)).thenReturn(mockedEntityList().filter { it.title == name })

            val result = repository.getVideoGamesByCoincidence(name)

            assert(result is VideoGameResult.Success<*>)
            assertEquals(expected, result)
        }

    @Test
    fun `should return VideoGameResult Success empty when repository calls getVideoGamesByCoincidence without no matches`() =
        runTest {
            val name = "mocked name"
            val expected = VideoGameResult.Success(mockedModelList().filter { it.title == name })

            mockitoWhen(dao.getByTitle(name)).thenReturn(mockedEntityList().filter { it.title == name })

            val result = repository.getVideoGamesByCoincidence(name)

            assert(result is VideoGameResult.Success<*>)
            assert((result as VideoGameResult.Success<List<VideoGameModel>>).data.isEmpty())
            assertEquals(expected, result)
        }

    @Test
    fun `should return VideoGameResult Success containing elements when repository calls getVideoGamesByGenre`() =
        runTest {
            val genre = mockedVideoGame().genre
            val expected = VideoGameResult.Success(mockedModelList().filter { it.genre == genre })

            mockitoWhen(dao.getByTitle(genre)).thenReturn(mockedEntityList().filter { it.genre == genre })

            val result = repository.getVideoGamesByGenre(genre)

            assert(result is VideoGameResult.Success<*>)
            assertEquals(expected, result)
        }

    @Test
    fun `should return VideoGameResult Success empty when repository calls getVideoGamesByGenre without matches`() =
        runTest {
            val genre = "mocked genre"
            val expected = VideoGameResult.Success(mockedModelList().filter { it.genre == genre })

            mockitoWhen(dao.getByTitle(genre)).thenReturn(mockedEntityList().filter { it.genre == genre })

            val result = repository.getVideoGamesByCoincidence(genre)

            assert(result is VideoGameResult.Success<*>)
            assert((result as VideoGameResult.Success<List<VideoGameModel>>).data.isEmpty())
            assertEquals(expected, result)
        }

    @Test
    fun `should return VideoGameResult Error containing ResponseError GENERIC when dao throws an exception`() =
        runTest {
            val expected = VideoGameResult.Error(ResponseError.GENERIC)
            mockitoWhen(dao.insert(any())).thenThrow()

            val result = repository.saveVideoGames(listOf())

            assert(result is VideoGameResult.Error)
            assertEquals(expected, result)
        }

    @Test
    fun `should return VideoGameResult Error containing ResponseError WRITING_READING when repository throws an IOException`() =
        runTest {
            val expected = VideoGameResult.Error(ResponseError.WRITING_READING)
            mockitoWhen(dao.getAll()).thenAnswer { throw IOException() }

            val result = repository.getAllVideoGames()

            assert(result is VideoGameResult.Error)
            assertEquals(expected, result)
        }
}
