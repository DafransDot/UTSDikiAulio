package com.example.utsdikiaulio.data.retrofit;

import com.example.utsdikiaulio.data.response.SearchResponse;
import com.example.utsdikiaulio.data.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @Headers("Authorization: token <TOken>")
    @GET("users/{username}")
    Call<UserResponse> getUser(@Path("username") String username);

    @Headers("Authorization: token <Token>")
    @GET("search/users")
    Call<SearchResponse> searchUser(@Query("q") String query);









}
