package com.example.demoapplication.viewModel.home


import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demoapplication.networking.responsePojo.home.MainListResponse
import com.example.demoapplication.networking.responsePojo.home.ResponseResult
import com.example.demoapplication.networking.responsePojo.login.loginMessage
import com.example.demoapplication.repository.home.HomeRepository
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

class HomeViewModel : ViewModel() {


    //Access to repository
    private var homeRepository: HomeRepository? = null
    private var compositeDisposable: CompositeDisposable? = null
    //Access from view
    var mutableLiveData: MutableLiveData<ResponseResult>

    init {
        homeRepository = HomeRepository()
        compositeDisposable = CompositeDisposable()
        mutableLiveData = MutableLiveData()
    }


    @SuppressLint("CheckResult")
    fun requestResponse(
        headers: HashMap<String, String>,
        action: String,
        pageNo: Int,
        timeZone:String
    ) {
        homeRepository?.requestResponse(headers,action, pageNo, timeZone)
            ?.doOnError {
                Timber.e(it)
            }?.subscribeWith(object : SingleObserver<MainListResponse> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable?.add(d)
                }

                override fun onSuccess(t: MainListResponse) {
                   if(t.response?.code == 200){

                       if(t.responseResult!=null){
                           mutableLiveData.postValue(t.responseResult)
                       }


                   }else{
                        //
                   }
                }

                override fun onError(e: Throwable) {
                    Timber.e(e)
                }


            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable?.clear()
    }
}