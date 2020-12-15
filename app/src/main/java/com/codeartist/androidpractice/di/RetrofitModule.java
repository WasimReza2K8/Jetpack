package com.codeartist.androidpractice.di;

import com.codeartist.androidpractice.network.RequestApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public class RetrofitModule {
    public static final String BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com";
    @Singleton
    @Provides
    public Retrofit.Builder provideBuilder(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
    }

    @Singleton
    @Provides
    public RequestApi provideRequestAPI(Retrofit.Builder retrofit) {
        return retrofit
                .build()
                .create(RequestApi.class);
    }
}
