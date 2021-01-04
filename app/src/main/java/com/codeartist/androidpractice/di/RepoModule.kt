package com.codeartist.androidpractice.di

import com.codeartist.androidpractice.db.PropertyDao
import com.codeartist.androidpractice.network.RequestApi
import com.codeartist.androidpractice.repository.PropertyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(dao: PropertyDao, requestApi: RequestApi): PropertyRepository {
        return PropertyRepository(dao, requestApi)
    }
}