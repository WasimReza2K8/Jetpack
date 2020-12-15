package com.codeartist.androidpractice.di;

import android.content.Context;

import androidx.room.Room;

import com.codeartist.androidpractice.db.PropertyDao;
import com.codeartist.androidpractice.db.PropertyRoomDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@InstallIn(ApplicationComponent.class)
@Module
public class DatabaseModule {
    @Singleton
    @Provides
    public PropertyRoomDatabase provideDatabase(Context context){
        return Room.databaseBuilder(context.getApplicationContext(),
                PropertyRoomDatabase.class, "word_database")
                .build();
    }

    @Singleton
    @Provides
    public PropertyDao provideDao(PropertyRoomDatabase propertyRoomDatabase){
        return propertyRoomDatabase.propertyDao();
    }
}
