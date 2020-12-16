/*
package com.codeartist.androidpractice.di;

import com.codeartist.androidpractice.db.PropertyDao;
import com.codeartist.androidpractice.network.RequestApi;
import com.codeartist.androidpractice.repository.Repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class RepositoryModule {
    @Singleton
    @Provides
    public Repository provideRepository(PropertyDao dao, RequestApi requestApi){
        return new Repository(dao, requestApi);

    }
}
*/
