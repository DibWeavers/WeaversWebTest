package com.example.demoapplication.utility.network

interface ConnectivityReceiverListener {
    fun onNetworkConnectionChanged(isConnected: Boolean)
}