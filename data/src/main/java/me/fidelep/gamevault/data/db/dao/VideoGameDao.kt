package me.fidelep.gamevault.data.db.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import me.fidelep.gamevault.data.db.model.VideoGameEntity

interface VideoGameDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg videogames: VideoGameEntity)

    @Update
    suspend fun update(videogame: VideoGameEntity): Long

    @Query("SELECT * FROM ${VideoGameEntity.TABLE_NAME}")
    suspend fun getAll(): List<VideoGameEntity>

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
