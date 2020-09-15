
package com.example.demoapplication.utility.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager


/**
 * This class ensure the network connection changes and notify to the respective callbacks.
 * Created by Dcc on 17/01/2020.
 */
class ConnectivityReceiver internal constructor(private val mConnectivityReceiverListener: ConnectivityReceiverListener) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        mConnectivityReceiverListener.onNetworkConnectionChanged(
            isConnected(
                context
            )
        )
    }

    companion object {
        fun isConnected(context: Context): Boolean {
            val cm =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }
    }

}