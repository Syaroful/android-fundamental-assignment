package com.syaroful.myandroidassignment.data.retrofit


import com.syaroful.myandroidassignment.data.response.DetailUserResponse
import com.syaroful.myandroidassignment.data.response.ItemsItem
import com.syaroful.myandroidassignment.data.response.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    fun getUser(@Query("q") query: String): Call<UserResponse>

    @GET("users/{username}")
    fun getUserDetail(@Path("username") username: String): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<List<ItemsItem>>

    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String): Call<List<ItemsItem>>
}