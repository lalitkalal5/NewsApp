package com.example.newsnewsapp.api

import com.example.newsnewsapp.models.NewsResponce
import com.example.newsnewsapp.util.Constants.Companion.api_key
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Apiinterface {

   @GET("v2/top-headlines?country=in&apiKey=0a1f94413f1043a6b0a4e07a20300388")
    suspend fun Gethdind(
       @Query("page")
       pagenumber :Int = 1,
       @Query("Apikey")
       apikey:String = api_key
   ): Response<NewsResponce>

   @GET("v2/top-headlines?country=us&apiKey=0a1f94413f1043a6b0a4e07a20300388")
   suspend fun us(
      @Query("page")
      pagenumber :Int = 1,
      @Query("Apikey")
      apikey:String = api_key
   ): Response<NewsResponce>
   @GET("v2/top-headlines?country=in&category=sports&apiKey=0a1f94413f1043a6b0a4e07a20300388")
   suspend fun sports(
      @Query("page")
      pagenumber :Int = 1,
      @Query("Apikey")
      apikey:String = api_key
   ): Response<NewsResponce>
   @GET("v2/top-headlines?country=in&category=health&apiKey=0a1f94413f1043a6b0a4e07a20300388")
   suspend fun health(
      @Query("page")
      pagenumber :Int = 1,
      @Query("Apikey")
      apikey:String = api_key
   ): Response<NewsResponce>
   @GET("v2/top-headlines?country=in&category=business&apiKey=0a1f94413f1043a6b0a4e07a20300388")
   suspend fun business(
      @Query("page")
      pagenumber :Int = 1,
      @Query("Apikey")
      apikey:String = api_key
   ): Response<NewsResponce>
   @GET("v2/top-headlines?country=in&category=science&apiKey=0a1f94413f1043a6b0a4e07a20300388")
   suspend fun science(
      @Query("page")
      pagenumber :Int = 1,
      @Query("Apikey")
      apikey:String = api_key
   ): Response<NewsResponce>

   @GET("v2/top-headlines?country=in&category=technology&apiKey=0a1f94413f1043a6b0a4e07a20300388")
   suspend fun technology(
      @Query("page")
      pagenumber :Int = 1,
      @Query("Apikey")
      apikey:String = api_key
   ): Response<NewsResponce>
   @GET("https://newsapi.org/v2/everything")
   suspend fun search(
      @Query("page")
      pagenumber: Int = 1,
      @Query("Apikey")
      apikey: String = api_key,
      @Query ("q") Searchvalue: String
   ): Response<NewsResponce>
}