package com.me.guanpj.mall.library.common

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*

class AppManager private constructor() {
    private val activityStack: Stack<Activity> = Stack()

    companion object {
        val instant: AppManager by lazy { AppManager() }
    }

    fun addActivity(activity: Activity) {
        activityStack.add(activity)
    }

    fun finishActivity(activity: Activity) {
        activity.finish()
        activityStack.remove(activity)
    }

    fun getTopActivity(): Activity {
        return activityStack.lastElement()
    }

    fun finishAllActivity() {
        for (activity in activityStack) {
         activity.finish()
        }
        activityStack.clear()
    }

    fun exitApp(context: Context) {
        finishAllActivity()
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.killBackgroundProcesses(context.packageName)
        System.exit(0)
    }
}