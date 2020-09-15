package com.example.demoapplication.networking.responsePojo.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginRes(
    @SerializedName("serverResponse")
    @Expose
    val serverRes:ServerResponse?,
    @SerializedName("result")
    @Expose
    val serverResult:ServerResult?,
)