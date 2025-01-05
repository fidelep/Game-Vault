package me.fidelep.gamevault.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.fidelep.gamevault.BuildConfig
import me.fidelep.gamevault.data.api.VideoGamesApi
import me.fidelep.gamevault.data.db.GameVaultDatabase
import me.fidelep.gamevault.data.db.dao.VideoGameDao
import me.fidelep.gamevault.data.repository.LocalRepository
import me.fidelep.gamevault.data.repository.RemoteRepository
import me.fidelep.gamevault.domain.interfaces.ILocalRepository
import me.fidelep.gamevault.domain.interfaces.IRemoteRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): GameVaultDatabase = GameVaultDatabase.create(context)

    @Singleton
    @Provides
    fun providedApi(): VideoGamesApi = VideoGamesApi.create(BuildConfig.GAME_VAULT_BASE_URL)

    @Singleton
    @Provides
    fun bindVideoGamesDao(database: GameVaultDatabase): VideoGameDao = database.videoGameDao()

    @Singleton
    @Provides
    fun provideRemoteRepository(api: VideoGamesApi): IRemoteRepository = RemoteRepository(api)

    @Singleton
    @Provides
    fun bindLocalRepository(dao: VideoGameDao): ILocalRepository = LocalRepository(dao)
}
