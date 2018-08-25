package me.vponomarenko.injectionmanager.x

import me.vponomarenko.injectionmanager.InjectionManager

/**
 * Author: Valery Ponomarenko
 * Date: 25/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

object XInjectionManager {
    val instance = InjectionManager(XLifecycleListener())
}