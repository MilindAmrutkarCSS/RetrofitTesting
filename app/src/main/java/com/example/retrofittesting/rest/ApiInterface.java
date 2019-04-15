package com.example.retrofittesting.rest;

import com.example.retrofittesting.model.Post;
import com.example.retrofittesting.model.UserResponse;
import com.example.retrofittesting.model.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("5cb052473100009c21e1334c")
    Call<List<Users>> getUserList();

    @POST("post")
    Call<UserResponse> createPost(@Query("token") String token, @Body Users users);

}
