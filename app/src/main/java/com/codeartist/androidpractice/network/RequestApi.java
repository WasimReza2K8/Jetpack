package com.codeartist.androidpractice.network;

import com.codeartist.androidpractice.model.Property;

import java.util.ArrayList;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface RequestApi {
    @GET("realestate")
    Flowable<ArrayList<Property>> makeQuery();
}