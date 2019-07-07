package com.example.omera.oppdeneme

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroClient {
    companion object{

        fun getClient(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://restcountries.eu/rest/")
                .addConverterFactory(GsonConverterFactory.create()).build()
        }}
}