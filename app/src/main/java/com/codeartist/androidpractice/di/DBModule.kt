package com.codeartist.androidpractice.di

import android.content.Context
import androidx.room.Room
import com.codeartist.androidpractice.db.PropertyDao
import com.codeartist.androidpractice.db.PropertyRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): PropertyRoomDatabase {
        return Room.databaseBuilder(context.applicationContext,
                PropertyRoomDatabase::class.java, "word_database")
                .build()
    }

    @Singleton
    @Provides
    fun provideDao(propertyRoomDatabase: PropertyRoomDatabase): PropertyDao {
        return propertyRoomDatabase.propertyDao()
    }
}