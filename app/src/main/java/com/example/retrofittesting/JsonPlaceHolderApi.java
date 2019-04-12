package com.example.retrofittesting;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("posts")
        // since it is jsonplaceholder.typicode.com/posts. Here we put posts which is relative url and we'll put base url somewhere else
    Call<List<Post>> getPosts();

   /* @GET("posts/2/comments")
    Call<List>
    */

   @GET("5cb04f753100009c21e1333d")
   Call<List<Users>> getUserList();
}
