package com.codeartist.androidpractice.network

import com.codeartist.androidpractice.model.Property
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.Flowable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import java.util.*


interface RequestApi {
    @GET("realestate")
    suspend fun getProperties(): List<Property>
}


private val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"


private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

// val retrofit = retrofitBuilder.build()
//requestApi = retrofit.create(RequestApi::class.java)

object MarsApi {
    val retrofitService: RequestApi by lazy { retrofit.create(RequestApi::class.java) }
}