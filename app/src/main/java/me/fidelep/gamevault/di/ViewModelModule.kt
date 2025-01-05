package me.fidelep.gamevault.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import me.fidelep.gamevault.domain.interfaces.ILocalRepository
import me.fidelep.gamevault.domain.interfaces.IRemoteRepository
import me.fidelep.gamevault.domain.usecase.DownloadVideoGamesUseCase
import me.fidelep.gamevault.domain.usecase.GetVideoGamesUseCase
import me.fidelep.gamevault.domain.usecase.RemoveVideoGameUseCase
import me.fidelep.gamevault.domain.usecase.SaveVideoGamesUseCase
import me.fidelep.gamevault.domain.usecase.UpdateVideoGameUseCase

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {
    @ViewModelScoped
    @Provides
    fun provideSaveVideoGamesUseCase(localRepository: ILocalRepository): SaveVideoGamesUseCase = SaveVideoGamesUseCase(localRepository)

    @ViewModelScoped
    @Provides
    fun provideGetVideoGamesUseCase(localRepository: ILocalRepository): GetVideoGamesUseCase = GetVideoGamesUseCase(localRepository)

    @ViewModelScoped
    @Provides
    fun provideDownloadVideoGamesUseCase(
        remoteRepository: IRemoteRepository,
        saveVideoGamesUseCase: SaveVideoGamesUseCase,
        getVideoGamesUseCase: GetVideoGamesUseCase,
    ): DownloadVideoGamesUseCase = DownloadVideoGamesUseCase(remoteRepository, saveVideoGamesUseCase, getVideoGamesUseCase)

    @ViewModelScoped
    @Provides
    fun provideRemoveVideoGameUseCase(localRepository: ILocalRepository): RemoveVideoGameUseCase = RemoveVideoGameUseCase(localRepository)

    @ViewModelScoped
    @Provides
    fun provideUpdateVideoGameUseCase(localRepository: ILocalRepository): UpdateVideoGameUseCase = UpdateVideoGameUseCase(localRepository)
}
