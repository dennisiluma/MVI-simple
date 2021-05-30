package com.decagon.android.sq007.utilities.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetofitInstance {


    const val BASE_URL = "https://jsonplaceholder.typicode.com"
    //build your retrofit which will be used to send request to the api url passed as a parameter to the baseUrl method,
   //by lazy variable declaration means it will only run when it is neede
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //map the JsonPlaceHolderApi class, use the built api above to send a get request which can use the path and query specified in the JsonPlaceHolderApi Class
    val api:JsonPlaceHolderApi by lazy {
        retrofit.create(JsonPlaceHolderApi::class.java)
    }
}