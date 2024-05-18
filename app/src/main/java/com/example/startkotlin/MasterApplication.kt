package com.example.startkotlin

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log

class MasterApplication : Application() {
    val TAG = "MyApplicationContext"
    val userId = 1000
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG,"onCreate")
        registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                Log.d(TAG,"$activity onActivityCreated")
            }

            override fun onActivityStarted(activity: Activity) {
                Log.d(TAG,"$activity onActivityStarted")
            }

            override fun onActivityResumed(activity: Activity) {
                Log.d(TAG,"$activity onActivityResumed")
            }

            override fun onActivityPaused(activity: Activity) {
                Log.d(TAG,"$activity onActivityPaused")
            }

            override fun onActivityStopped(activity: Activity) {
                Log.d(TAG,"$activity onActivityStopped")
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                Log.d(TAG,"$activity onActivitySaveInstanceState")
            }

            override fun onActivityDestroyed(activity: Activity) {
                Log.d(TAG,"$activity onActivityDestroyed")
            }
        })
    }

    fun methodOfApplication() {
        Log.d(TAG,"This is fn")
    }
}