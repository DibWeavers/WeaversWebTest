package com.example.demoapplication.networking.responsePojo.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MainListResponse(
    @SerializedName("serverResponse")
    @Expose
    val response:ServerResponseStatus?,
    @SerializedName("result")
    @Expose
    val responseResult: ResponseResult?
)
