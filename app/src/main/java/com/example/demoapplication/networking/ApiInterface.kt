package com.example.demoapplication.networking

import com.example.demoapplication.networking.responsePojo.home.MainListResponse
import com.example.demoapplication.networking.responsePojo.login.LoginRes
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*
import timber.log.Timber

interface ApiInterface {


    /**********************************todo Api Service's***********************************/
    //@Field parameters can only be used with form encoding (POST)
    //@Query This annotation represents any query key value pair to be sent along with the network request GET/POST
    //@Path parameter name must match \{([a-zA-Z][a-zA-Z0-9_-]*)\}.


    /*todo------((Api1--Login))*/
    @Multipart
    @POST
    fun fetchLogin(
        @Url url: String,
        @Part("action") action: RequestBody,
        @Part("email") email: RequestBody,
        @Part("password") password: RequestBody): Single<LoginRes>

    /*todo------((Api1--Listing))*/
    @Multipart
    @POST
    fun fetchListing(
        @HeaderMap headers: HashMap<String, String>,
        @Url url: String,
        @Part("action") action: RequestBody,
        @Part("pageNo") pageNo: RequestBody,
        @Part("timeZone") timeZone: RequestBody): Single<MainListResponse>




    /**Create Retrofit Service--By Calling class Create Retrofit**/
    class CreateRetrofit {
        fun apiService(url: String?): ApiInterface {
            Timber.e("""RetrofitUrl$url""")
            return when (url) {
                WebService.BaseUrl -> {
                    RetrofitNetworking.getClient(WebService.BaseUrl)!!.create(ApiInterface::class.java)
                }
                else -> {
                    Timber.e(url)
                    url?.let { RetrofitNetworking.getClient(it) }!!.create(ApiInterface::class.java)
                }
            }
        }
    }
}