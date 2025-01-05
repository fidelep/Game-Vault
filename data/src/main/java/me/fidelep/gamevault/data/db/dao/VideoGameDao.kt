package me.fidelep.gamevault.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import me.fidelep.gamevault.data.db.model.VideoGameEntity

@Dao
interface VideoGameDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg videogames: VideoGameEntity): LongArray

    @Update
    suspend fun update(videogame: VideoGameEntity): Int

    @Query(
        "SELECT * FROM ${VideoGameEntity.TABLE_NAME} " +
            "WHERE ${VideoGameEntity.COLUMN_IS_ACTIVE} = 1",
    )
    suspend fun getAll(): List<VideoGameEntity>

    // TODO: Change to get video games by title | developer | publisher or even consider a selectable filter params
    @Query(
        "SELECT * FROM ${VideoGameEntity.TABLE_NAME} " +
            "WHERE ${VideoGameEntity.COLUMN_TITLE} LIKE :title",
    )
    suspend fun getByTitle(title: String): List<VideoGameEntity>

    @Query(
        "SELECT * FROM ${VideoGameEntity.TABLE_NAME} " +
            "WHERE ${VideoGameEntity.COLUMN_GENRE} LIKE :genre",
    )
    suspend fun getByGenre(genre: String): List<VideoGameEntity>
}
