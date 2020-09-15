package com.example.demoapplication.utility.network

import android.app.Service
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.content.IntentFilter
import android.widget.Toast
import com.example.demoapplication.localDatabase.staticValue.StaticValue
import timber.log.Timber


/**
 * Service to handle callbacks from the JobScheduler. Requests scheduled with the JobScheduler
 * ultimately land on this service's "onStartJob" method.
 * @author Dcc
 */
class NetworkSchedulerService : JobService(), ConnectivityReceiverListener {
    private var mConnectivityReceiver: ConnectivityReceiver? = null
    override fun onCreate() {
        super.onCreate()
        Timber.i("Service created")
        mConnectivityReceiver = ConnectivityReceiver(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("Service destroyed")
    }

    /**
     * When the app's MainActivity is created, it starts this service. This is so that the
     * activity and this service can communicate back and forth. See "setUiCallback()"
     */
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Timber.i("onStartCommand")
        return Service.START_NOT_STICKY
    }

    override fun onStartJob(params: JobParameters): Boolean {
        Timber.i(
            "onStartJob$mConnectivityReceiver"
        )
        registerReceiver(mConnectivityReceiver, IntentFilter(StaticValue.CONNECTIVITY_ACTION))
        return true
    }

    override fun onStopJob(params: JobParameters): Boolean {
        Timber.i("onStopJob")
        unregisterReceiver(mConnectivityReceiver)
        return true
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        //if (ChatBotApplication.isInterestingActivityVisible()) {
        if (isConnected)
            Timber.e("Connected to Internet")
        else {
            Toast.makeText(applicationContext, "No Internet Connection", Toast.LENGTH_SHORT)
                .show()
        }
        /*} else {
            Timber.e("Else part")
        }*/
    }


}