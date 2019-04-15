package com.example.retrofittesting.rest;

import com.example.retrofittesting.model.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("5cb052473100009c21e1334c")
    Call<List<Users>> getUserList();
}
