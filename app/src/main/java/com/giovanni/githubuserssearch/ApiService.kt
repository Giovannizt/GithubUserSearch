package com.giovanni.githubuserssearch

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
        fun getUsers(@Query("q") page:String): Call<Response>

    @GET("users/{username}")
    fun getDetailUsers(@Path("username") username: String): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    fun getUserFollower(@Path("username") username: String): Call<List<ItemsItem>>

    @GET("users/{username}/following")
    fun getUserFollowing(@Path("username") username: String): Call<List<ItemsItem>>
}