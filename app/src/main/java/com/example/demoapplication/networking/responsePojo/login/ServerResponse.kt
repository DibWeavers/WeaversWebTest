package com.example.demoapplication.networking.responsePojo.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ServerResponse(
    @SerializedName("code")
    @Expose
    val responseCode: Int,
    @SerializedName("message")
    @Expose
    val responseMessage: String,
    @SerializedName("isSuccess")
    @Expose
    val isSuccess: Boolean,
)