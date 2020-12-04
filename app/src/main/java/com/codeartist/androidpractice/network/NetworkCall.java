package com.codeartist.androidpractice.network;

import com.codeartist.androidpractice.model.Property;

import java.util.ArrayList;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class NetworkCall {
    private static volatile NetworkCall instance = null;
    public static final String BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com";
    private RequestApi requestApi;
    private NetworkCall() {
        Retrofit.Builder retrofitBuilder =
                new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = retrofitBuilder.build();
        requestApi = retrofit.create(RequestApi.class);

    }

    public static NetworkCall getInstance() {
        if (instance == null) {
            synchronized(NetworkCall.class) {
                if (instance == null) {
                    instance = new NetworkCall();
                }
            }
        }
        return instance;
    }





    public RequestApi getRequestApi(){
        return requestApi;
    }

    public interface RequestApi {

        @GET("realestate")
        Flowable<ArrayList<Property>> makeQuery();
    }
}
