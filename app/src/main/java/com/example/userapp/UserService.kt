package com.example.userapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


val BASE_URL = "https://dummyjson.com/"

interface UserInterface{
    @GET("users")
    fun getUsers(@Query("page") page:Int):Call<ApiData>
}

object UserService{

    val userInstance: UserInterface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        userInstance = retrofit.create(UserInterface::class.java)
    }
}