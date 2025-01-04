package me.fidelep.gamevault.data.db

import androidx.room.Database
import me.fidelep.gamevault.data.db.GameVaultDatabase.Companion.DB_VERSION
import me.fidelep.gamevault.data.db.dao.VideoGameDao
import me.fidelep.gamevault.data.db.model.VideoGameEntity

@Database(entities = [VideoGameEntity::class], version = DB_VERSION, exportSchema = true)
abstract class GameVaultDatabase {
    abstract fun videoGameDao(): VideoGameDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "db_game_vault"
    }
}
