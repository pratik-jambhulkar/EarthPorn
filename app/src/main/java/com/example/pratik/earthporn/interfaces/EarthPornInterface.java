package com.example.pratik.earthporn.interfaces;

import com.example.pratik.earthporn.beans.Example;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by pratik on 22-12-2015.
 */
public interface EarthPornInterface {

    @GET("/r/earthporn.json")
    public void getImagesDetails(Callback<Example> response);
}

