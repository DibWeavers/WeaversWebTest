package com.example.demoapplication.networking

import android.text.TextUtils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

object RetrofitNetworking {
    private var retrofit: Retrofit? = null

    fun getClient(baseUrl: String): Retrofit? {
        Timber.e(baseUrl)
        if (retrofit == null || !TextUtils.isEmpty(baseUrl)) {
            //TODO While release in Google Play Change the Level to NONE
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)//EncryptDecryptInterceptor() --Encrypted Data Decrypt
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()

            retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())//GsonConverter
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//RxJava2
                .build()
        } else {
            Timber.e("Retrofit is not null || BaseUrl is null")
        }


        return retrofit

    }
}