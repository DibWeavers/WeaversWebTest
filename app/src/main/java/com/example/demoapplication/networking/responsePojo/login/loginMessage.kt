package com.example.demoapplication.networking.responsePojo.login

import io.reactivex.internal.operators.maybe.MaybeDoAfterSuccess

data class loginMessage (
    val message:String?,
    val isSuccess: Boolean?
)