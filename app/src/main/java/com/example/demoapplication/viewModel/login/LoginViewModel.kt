package com.example.demoapplication.viewModel.login

import android.annotation.SuppressLint
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demoapplication.DemoApplication.Companion.getPref
import com.example.demoapplication.localDatabase.staticValue.StaticValue
import com.example.demoapplication.networking.responsePojo.login.LoginRes
import com.example.demoapplication.networking.responsePojo.login.loginMessage
import com.example.demoapplication.repository.login.LoginRepository
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

class LoginViewModel:ViewModel() {

    //Access to repository
    private var loginRepository: LoginRepository? = null
    private var compositeDisposable: CompositeDisposable? = null
    //Access from view
    var mutableLiveData: MutableLiveData<loginMessage>

    init {
        loginRepository = LoginRepository()
        compositeDisposable = CompositeDisposable()
        mutableLiveData = MutableLiveData()
    }

    @SuppressLint("CheckResult")
    fun loginApi(
    email: String,
    password: String,
    action:String
    ){
                loginRepository?.loginApi(email, password, action)
            ?.doOnError {
                Timber.e(it)
            }?.subscribeWith(object : SingleObserver<LoginRes> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable?.add(d)
                }

                override fun onError(e: Throwable) {
                    Timber.e(e)
                }

                override fun onSuccess(response: LoginRes) {
                    Timber.e(response.serverRes?.responseMessage)
                    if(response.serverRes?.responseCode == 200){
                        if(response.serverResult !=null){

                            mutableLiveData.postValue(loginMessage(response.serverRes.responseMessage,response.serverRes.isSuccess))
                            //Save Accesstoken
                            getPref().setPrefValue(
                                StaticValue.headerKey,
                                response.serverResult.profileResponse.accessToken
                            )
                        }
                    }else{
                        mutableLiveData.postValue(loginMessage(response.serverRes?.responseMessage,response.serverRes?.isSuccess))
                    }
                }
                })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable?.clear()
    }


}