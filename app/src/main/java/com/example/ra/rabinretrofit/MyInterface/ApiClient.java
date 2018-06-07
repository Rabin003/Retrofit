package com.example.ra.rabinretrofit.MyInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by RA on 4/7/2018.
 */

public class ApiClient {
    private static Retrofit retrofit=null;
    private static final String BASE_URL="https://jsonplaceholder.typicode.com/";

    public static Retrofit getClient(){

        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;

    }
}
