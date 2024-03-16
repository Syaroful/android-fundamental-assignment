package com.syaroful.myandroidassignment.data.retrofit

import com.syaroful.myandroidassignment.data.response.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    @Headers("Authorization:token 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")
    fun getUser(
        @Query("q") query: String
    ): Call<UserResponse>
}