package com.me.guanpj.mall.library.core

import android.app.Activity
import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import java.util.*

class AppStatusTracker private constructor(private val application: Application?) : Application.ActivityLifecycleCallbacks {
    private var receiver: DeamonReceiver? = null
    private var allActivities: MutableSet<Activity>? = null
    private var appStatus = AppStatus.STATUS_FORCE_KILLED
    private var isForground = false
    private var activeCount = 0
    private var timestamp: Long = 0
    private var isScreenOff = false

    companion object {
        var instance: AppStatusTracker? = null

        fun init(application: Application) {
            instance = AppStatusTracker(application)
        }
    }

    fun setAppStatus(status: Int) {
        appStatus = status
        if (this.appStatus == AppStatus.STATUS_ONLINE) {
            if (this.receiver == null) {
                val filter = IntentFilter()
                filter.addAction(Intent.ACTION_SCREEN_OFF)
                receiver = DeamonReceiver()
                this.application!!.registerReceiver(receiver, filter)
            }
        } else {
            if (this.application != null) {
                this.application.unregisterReceiver(receiver)
                receiver = null
            }
        }
    }

    override fun onActivityCreated(activity: Activity, bundle: Bundle) {
        addActivity(activity)
    }

    override fun onActivityStarted(activity: Activity) {
        activeCount++
    }

    override fun onActivityResumed(activity: Activity) {
        isForground = true
        timestamp = 0L
        isScreenOff = false
    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {
        activeCount--
        if (activeCount == 0) {
            isForground = false
            timestamp = System.currentTimeMillis()
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {

    }

    override fun onActivityDestroyed(activity: Activity) {
        removeActivity(activity)
    }

    fun addActivity(act: Activity) {
        if (allActivities == null) {
            allActivities = HashSet()
        }
        allActivities!!.add(act)
    }

    fun removeActivity(act: Activity) {
        if (allActivities != null) {
            allActivities!!.remove(act)
        }
    }

    fun exitApp() {
        if (allActivities != null) {
            for (act in allActivities!!) {
                act.finish()
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid())
        System.exit(0)
    }

    private fun onScreenOff(isScreenOff: Boolean) {
        this.isScreenOff = isScreenOff
    }

    private inner class DeamonReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (Intent.ACTION_SCREEN_OFF == intent.action) {
                instance!!.onScreenOff(true)
            }
        }
    }
}