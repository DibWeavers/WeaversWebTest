package com.example.demoapplication.utility

import android.util.Log
import timber.log.Timber

class ReleaseApkTreeLog : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.ERROR ||
            priority == Log.DEBUG || priority == Log.WARN ||
            priority == Log.INFO|| priority == Log.ASSERT
        ) {
            return
        }

        // log your crash to your favourite
        // Sending crash report to Firebase CrashAnalytics

        // FirebaseCrash.report(message);
        // FirebaseCrash.report(new Exception(message));
    }
}