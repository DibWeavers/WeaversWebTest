package com.example.demoapplication.networking.responsePojo.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ImageLists (
    @SerializedName("imageUrl")
    @Expose
    val imageUrl:String,
)