package com.example.demoapplication.localDatabase.sharedPreference

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import timber.log.Timber

class SharedPreferenceImpl(context: Context?): ISharedPreference {

    private var sharedPref: SharedPreferences? =
        context?.let { SharedPreference(context = it).providePreferences() }


    override fun setPrefValue(key: String, value: Any?) {
        when (value) {
            is String? -> {
                sharedPref?.edit()?.putString(key, value)?.apply()
                Timber.e(value)
            }
            is Int -> {
                sharedPref?.edit()?.putInt(key, value)?.apply()
                Timber.e("""$value""")
            }
            is Boolean -> {
                sharedPref?.edit()?.putBoolean(key, value)?.apply()
                Timber.e("""$value""")
            }
            is Float -> {
                sharedPref?.edit()?.putFloat(key, value)?.apply()
                Timber.e("""$value""")
            }
            is Long -> {
                sharedPref?.edit()?.putLong(key, value)?.apply()
                Timber.e("""$value""")
            }
            is ArrayList<*> -> {
                val gson = Gson()
                val json = gson.toJson(value)
                sharedPref?.edit()?.putString(key, json)?.apply()
            }

            else -> throw UnsupportedOperationException("Not supported")
        }
    }




    override fun clearPrefValue() {
        sharedPref?.edit()?.clear()?.apply()
        Timber.e("""Cleared""")
    }

    override fun removeKeyPrefValue(key: String) {
        sharedPref?.edit()?.remove(key as String?)?.apply()
        Timber.e(key, "Cleared-K-$key")
    }

    override fun getStringValue(key: String): String? {
        return sharedPref?.getString(key, null)
    }

    override fun getIntValue(key: String): Int? {
        return sharedPref?.getInt(key, 0)
    }

    override fun getBooleanValue(key: String): Boolean? {
        return sharedPref?.getBoolean(key, false)
    }

    override fun getFloatValue(key: String): Float? {
        return sharedPref?.getFloat(key, 0.0f)
    }

    override fun getLongValue(key: String): Long? {
        return sharedPref?.getLong(key, 0L)
    }

    override fun getListValue(key: String): ArrayList<String> {
        val gson = Gson()
        val json = sharedPref?.getString(key, null)
        val type = object : TypeToken<ArrayList<String>>() {

        }.type
        return if (json != null) {
            gson.fromJson(json, type)
        } else
            ArrayList()
    }





}