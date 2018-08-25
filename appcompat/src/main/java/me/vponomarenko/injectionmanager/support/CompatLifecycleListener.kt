package me.vponomarenko.injectionmanager.support

import android.app.Application
import me.vponomarenko.injectionmanager.callbacks.ILifecycleListener
import me.vponomarenko.injectionmanager.callbacks.IRemoveComponentCallback

/**
 * Author: Valery Ponomarenko
 * Date: 25/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

internal class CompatLifecycleListener : ILifecycleListener {
    override fun addLifecycleListener(app: Application, removeCallback: IRemoveComponentCallback) {
        app.registerActivityLifecycleCallbacks(CompatActivityLifecycleHelper(removeCallback))
    }
}