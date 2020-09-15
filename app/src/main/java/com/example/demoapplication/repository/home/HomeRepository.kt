package com.example.demoapplication.repository.home


import android.annotation.SuppressLint
import android.util.Log
import com.example.demoapplication.localDatabase.staticValue.StaticValue
import com.example.demoapplication.networking.ApiInterface
import com.example.demoapplication.networking.WebService
import com.example.demoapplication.networking.responsePojo.home.MainListResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.RequestBody
import timber.log.Timber


class HomeRepository {
    private var dashboardRepoApiService: ApiInterface? = null
    init {
        dashboardRepoApiService = ApiInterface.CreateRetrofit().apiService(WebService.BaseUrl)
    }
    @SuppressLint("CheckResult")
    fun requestResponse(
        headerMap: HashMap<String, String>,
        action: String,
        pageNo: Int,
        timeZone:String,

    ): Single<MainListResponse>? {
        Log.e("1",action)
        Log.e("2",pageNo.toString())
        Log.e("3",timeZone)
        return dashboardRepoApiService?.fetchListing(
            headerMap,
            "",
            RequestBody.create(MediaType.parse("text/plain"),action),
            RequestBody.create(MediaType.parse("text/plain"),pageNo.toString()),
            RequestBody.create(MediaType.parse("text/plain"),timeZone),)
            ?.doOnError {
                Timber.e(it)
            }?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
    }

}