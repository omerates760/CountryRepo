package com.example.omera.oppdeneme

import com.google.gson.annotations.SerializedName

data class Results (val name:String, val alpha2Code:String, val alpha3Code:String,val callingCodes:List<String>
                    , val capital:String, val region:String, val population:String, val area:String, val flag:String)

//data class CallingCodes(@SerializedName("0")val num:List<String>)
//data class LatLng(val latitude:String,val longitude:String)
