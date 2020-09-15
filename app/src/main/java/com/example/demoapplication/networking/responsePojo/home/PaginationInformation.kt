package com.example.demoapplication.networking.responsePojo.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PaginationInformation (
    @SerializedName("pageNo")
    @Expose
    val pageNo:Int,
    @SerializedName("postPerPage")
    @Expose
    val postPerPage:Int,
    @SerializedName("totalPosts")
    @Expose
    val totalPosts:Int,

)