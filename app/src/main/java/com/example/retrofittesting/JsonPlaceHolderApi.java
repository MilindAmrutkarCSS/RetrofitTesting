package com.example.retrofittesting;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("5cb052473100009c21e1334c")
    Call<List<Users>> getUserList();
}
