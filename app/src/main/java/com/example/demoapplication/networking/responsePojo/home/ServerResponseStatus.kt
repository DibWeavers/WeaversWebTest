package com.example.demoapplication.networking.responsePojo.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ServerResponseStatus (
    @SerializedName("code")
    @Expose
    val code:Int,
    @SerializedName("message")
    @Expose
    val message:String,
    @SerializedName("isSuccess")
    @Expose
    val isSuccess:Boolean,
)