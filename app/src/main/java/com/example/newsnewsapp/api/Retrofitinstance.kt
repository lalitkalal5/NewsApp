package com.example.newsnewsapp.api

import com.example.newsnewsapp.util.Constants.Companion.base_url
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class Retrofitinstance {
    companion object {
        private val retrofit by lazy{
            Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val service :Apiinterface by lazy {
            retrofit.create(Apiinterface :: class.java)
        }
    }
}