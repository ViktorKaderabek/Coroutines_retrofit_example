package com.example.coroutines_example.data.api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    interface ApiInterface {

        @GET("Startovacky")
        fun getMovies() : Call<List<MyDataItem>>

        companion object {

            var BASE_URL = "https://61bc9a15d8542f0017824870.mockapi.io/"

            fun create() : ApiInterface {

                val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
                return retrofit.create(ApiInterface::class.java)

            }


        }
    }
}