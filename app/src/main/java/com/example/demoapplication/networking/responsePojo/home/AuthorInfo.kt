package com.example.demoapplication.networking.responsePojo.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AuthorInfo (
    @SerializedName("fullName")
    @Expose
    val fullName:String
)