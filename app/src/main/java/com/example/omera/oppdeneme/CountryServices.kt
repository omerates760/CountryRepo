package com.example.omera.oppdeneme

import retrofit2.Call
import retrofit2.http.*

interface CountryServices {

    @GET("v2")
    fun getRegion(): Call<List<Results>>

    @GET("v2/region/{region}")
    fun getCountries(@Path("region")region:String):Call<List<Results>>

    @GET("v2/name/{name}")
    fun getCountry(@Path("name")name:String):Call<List<Results>>
}