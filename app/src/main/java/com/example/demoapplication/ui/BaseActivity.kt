package com.example.demoapplication.ui

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.demoapplication.utility.network.ConnectivityReceiver
import com.example.demoapplication.utility.network.NetworkSchedulerService


abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView()?.let {
            setContentView(it)
        }

        scheduleJob()
        checkConnection()
    }

    //Setting layout dynamically
    abstract fun setContentView():Int?


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private fun scheduleJob() {
        val myJob =
            JobInfo.Builder(0, ComponentName(this, NetworkSchedulerService::class.java))
                .setRequiresCharging(true)
                .setMinimumLatency(1000)
                .setOverrideDeadline(2000)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .build()
        val jobScheduler =
            getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.schedule(myJob)
    }


    override fun onStop() {
        // A service can be "started" and/or "bound". In this case, it's "started" by this Activity
        // and "bound" to the JobScheduler (also called "Scheduled" by the JobScheduler). This call
        // to stopService() won't prevent scheduled jobs to be processed. However, failing
        // to call stopService() would keep it alive indefinitely.
        stopService(Intent(this, NetworkSchedulerService::class.java))
        super.onStop()
    }

    override fun onStart() {
        super.onStart()
        // Start service and provide it a way to communicate with this class.
        val startServiceIntent = Intent(this, NetworkSchedulerService::class.java)
        startService(startServiceIntent)
    }

    // Method to manually check connection status
    private fun checkConnection() {
        val isConnected = ConnectivityReceiver.isConnected(context = applicationContext)
        val message = if (isConnected)
            "Connected to Internet"
        else
            "No Internet Connection"
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }


}
