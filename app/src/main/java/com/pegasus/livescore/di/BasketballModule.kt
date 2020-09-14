package com.pegasus.livescore.di

import com.pegasus.livescore.database.dao.BasketballDao
import com.pegasus.livescore.database.datasource.AppDatabase
import com.pegasus.livescore.database.datasource.basketball.BasketballDataSource
import com.pegasus.livescore.database.repository.BasketballRepository
import com.pegasus.livescore.database.service.BasketballService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object BasketballModule {
    @Provides
    fun provideBasketballService(retrofit: Retrofit): BasketballService = retrofit.create(
        BasketballService::class.java)

    @Provides
    fun provideBasketballRemoteDataSource(BasketballService: BasketballService) = BasketballDataSource(BasketballService)

    @Singleton
    @Provides
    fun provideBasketballDao(db: AppDatabase) = db.basketballDao()

    @Provides
    fun provideRepository(remoteDataSource: BasketballDataSource,
                          localDataSource: BasketballDao
    ) =
        BasketballRepository(remoteDataSource, localDataSource)
}
