package com.example.demoapplication.networking.responsePojo.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseResult(
    @SerializedName("postLists")
    @Expose
    val postList: ArrayList<PostList>?,
    @SerializedName("paginationInfo")
    @Expose
    val paginationInformation:PaginationInformation
)