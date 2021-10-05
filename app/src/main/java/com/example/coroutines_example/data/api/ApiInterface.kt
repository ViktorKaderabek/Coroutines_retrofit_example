package com.example.coroutines_example.data.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("posts")
    fun getData() : Call<List<MyDataItem>>
}