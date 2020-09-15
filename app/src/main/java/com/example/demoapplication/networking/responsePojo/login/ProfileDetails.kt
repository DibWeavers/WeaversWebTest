package com.example.demoapplication.networking.responsePojo.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfileDetails (
    @SerializedName("accessToken")
    @Expose
    val accessToken:String,
    @SerializedName("fullName")
    @Expose
    val fullName:String,
    @SerializedName("userName")
    @Expose
    val userName:String,
    @SerializedName("email")
    @Expose
    val email:String,
    @SerializedName("profileImageURL")
    @Expose
    val profileImageURL:String,
    @SerializedName("encryptedUserId")
    @Expose
    val encryptedUserId:String,
    @SerializedName("phoneCountryCode")
    @Expose
    val phoneCountryCode:String,
    @SerializedName("phone")
    @Expose
    val phone:String,
    @SerializedName("isFirstTime")
    @Expose
    val isFirstTime:String,
    @SerializedName("signupType")
    @Expose
    val signupType:String,
    @SerializedName("statusText")
    @Expose
    val statusText:String,

)