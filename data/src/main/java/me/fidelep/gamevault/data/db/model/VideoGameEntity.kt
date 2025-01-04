package me.fidelep.gamevault.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import me.fidelep.gamevault.data.db.model.VideoGameEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class VideoGameEntity(
    @ColumnInfo(name = COLUMN_ID)
    val id: Int,
    @ColumnInfo(name = COLUMN_TITLE)
    val title: String,
    @ColumnInfo(name = COLUMN_THUMBNAIL)
    val thumbnail: String,
    @ColumnInfo(name = COLUMN_SHORT_DESCRIPTION)
    val shortDescription: String,
    @ColumnInfo(name = COLUMN_GAME_URL)
    val gameUrl: String,
    @ColumnInfo(name = COLUMN_GENRE)
    val genre: String,
    @ColumnInfo(name = COLUMN_PLATFORM)
    val platform: String,
    @ColumnInfo(name = COLUMN_PUBLISHER)
    val publisher: String,
    @ColumnInfo(name = COLUMN_DEVELOPER)
    val developer: String,
    @ColumnInfo(name = COLUMN_RELEASE_DATE)
    val releaseDate: String,
    @ColumnInfo(name = COLUMN_FREETOGAME_PROFILE_URL)
    val freetogameProfileUrl: String,
) {
    companion object {
        const val TABLE_NAME = "videogame"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_THUMBNAIL = "thumbnail"
        const val COLUMN_SHORT_DESCRIPTION = "short_description"
        const val COLUMN_GAME_URL = "game_url"
        const val COLUMN_GENRE = "genre"
        const val COLUMN_PLATFORM = "platform"
        const val COLUMN_PUBLISHER = "publisher"
        const val COLUMN_DEVELOPER = "developer"
        const val COLUMN_RELEASE_DATE = "release_date"
        const val COLUMN_FREETOGAME_PROFILE_URL = "freetogame_profile_url"
    }
}
