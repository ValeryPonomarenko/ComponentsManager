package me.vponomarenko.injectionmanager.support

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.callbacks.IRemoveComponentCallback

/**
 * Author: Valery Ponomarenko
 * Date: 25/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

internal class CompatActivityLifecycleHelper(
    private val removeComponentCallback: IRemoveComponentCallback
) : Application.ActivityLifecycleCallbacks {

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityDestroyed(activity: Activity) {
        if (activity is IHasComponent && activity.isFinishing) {
            removeComponentCallback.onRemove(activity.getComponentKey())
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivityCreated(activity: Activity, outState: Bundle?) {
        if (activity is AppCompatActivity) {
            activity.supportFragmentManager.registerFragmentLifecycleCallbacks(
                CompatFragmentLifecycleHelper(removeComponentCallback),
                true
            )
        }
    }
}