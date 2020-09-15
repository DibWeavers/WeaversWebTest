package com.example.demoapplication.localDatabase.sharedPreference

interface ISharedPreference {
    fun setPrefValue(key: String, value: Any?)
    fun clearPrefValue()
    fun removeKeyPrefValue(key: String)

    fun getStringValue(key: String): String?
    fun getIntValue(key: String): Int?
    fun getBooleanValue(key: String): Boolean?
    fun getFloatValue(key: String): Float?
    fun getLongValue(key: String): Long?
    fun getListValue(key: String): ArrayList<String>
}