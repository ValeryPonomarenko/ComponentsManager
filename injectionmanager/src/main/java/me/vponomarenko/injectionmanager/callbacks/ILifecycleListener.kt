package me.vponomarenko.injectionmanager.callbacks

import android.app.Application

/**
 * Author: Valery Ponomarenko
 * Date: 25/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

interface ILifecycleListener {
    fun addLifecycleListener(app: Application, removeCallback: IRemoveComponentCallback)
}