package com.pegasus.livescore.di

import com.pegasus.livescore.database.datasource.AppDatabase
import com.pegasus.livescore.database.datasource.football.FootballDataSource
import com.pegasus.livescore.database.repository.FootballRepository
import com.pegasus.livescore.database.service.FootballService
import com.pegasus.sport.data.dao.FootballDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object FootballModule {
    @Provides
    fun provideFootballService(retrofit: Retrofit): FootballService = retrofit.create(FootballService::class.java)

    @Provides
    fun provideFootballRemoteDataSource(footballService: FootballService) = FootballDataSource(footballService)

    @Singleton
    @Provides
    fun provideFootballDao(db: AppDatabase) = db.footballDao()

    @Provides
    fun provideRepository(remoteDataSource: FootballDataSource,
                          localDataSource: FootballDao) =
        FootballRepository(remoteDataSource, localDataSource)
}
