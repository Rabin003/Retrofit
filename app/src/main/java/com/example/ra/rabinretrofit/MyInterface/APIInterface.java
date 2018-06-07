package com.example.ra.rabinretrofit.MyInterface;

import com.example.ra.rabinretrofit.model.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by RA on 4/7/2018.
 */

public interface APIInterface {
    @GET("/photos")
    Call<List<Photo>> getAllPhotos();

    @GET("/photos/{id}")
    Call<Photo> getASinglePhoto(@Path("id") int photoId);

}
