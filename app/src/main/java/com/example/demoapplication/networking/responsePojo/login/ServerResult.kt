package com.example.demoapplication.networking.responsePojo.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ServerResult(
    @SerializedName("profileDetails")
    @Expose
    val profileResponse: ProfileDetails
)