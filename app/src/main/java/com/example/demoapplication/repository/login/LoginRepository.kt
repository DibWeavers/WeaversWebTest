package com.example.demoapplication.repository.login

import android.annotation.SuppressLint
import android.util.Log
import com.example.demoapplication.networking.ApiInterface
import com.example.demoapplication.networking.WebService
import com.example.demoapplication.networking.responsePojo.login.LoginRes
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.RequestBody
import timber.log.Timber

class LoginRepository {
    private var loginRepoApiService: ApiInterface? = null
    init {
        loginRepoApiService = ApiInterface.CreateRetrofit().apiService(WebService.BaseUrl)
    }

    @SuppressLint("CheckResult")
    fun loginApi(
        email: String,
        password: String,
        action:String
    ): Single<LoginRes>? {
        Log.e("email",email)
        Log.e("email",password)
        Log.e("email",action)
        return loginRepoApiService?.fetchLogin("",
            RequestBody.create(MediaType.parse("text/plain"),action),
            email= RequestBody.create(MediaType.parse("text/plain"),email),
            password = RequestBody.create(MediaType.parse("text/plain"),password))
            ?.doOnError {
                Timber.e(it)
            }?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
    }




}