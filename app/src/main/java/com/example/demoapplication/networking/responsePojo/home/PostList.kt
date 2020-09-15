package com.example.demoapplication.networking.responsePojo.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostList (
  @SerializedName("postDescription")
  @Expose
  val postDescription:String,
  @SerializedName("authorInfo")
  @Expose
  val authorInfo:AuthorInfo,
  @SerializedName("imageLists")
  @Expose
  val imageLists:ArrayList<ImageLists>


)