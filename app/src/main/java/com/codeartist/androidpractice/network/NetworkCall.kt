package com.codeartist.androidpractice.network

import com.codeartist.androidpractice.model.Property
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface RequestApi {
    @GET("realestate")
    suspend fun getProperties(): List<Property>
}


private val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"



/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

object MarsApi {
    val retrofitService: RequestApi by lazy { retrofit.create(RequestApi::class.java) }
}