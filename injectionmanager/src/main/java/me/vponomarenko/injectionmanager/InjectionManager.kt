package me.vponomarenko.injectionmanager

import android.app.Application
import me.vponomarenko.injectionmanager.helpers.ActivityLifecycleHelper

/**
 * Author: Valery Ponomarenko
 * Date: 25/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class InjectionManager {

    companion object {
        val instance by lazy { InjectionManager() }
    }

    private val componentManager = ComponentManager()

    @Suppress("UNCHECKED_CAST")
    fun <T> bindComponent(owner: IHasComponent): T {
        when (owner) {
            is Application -> addActivityCallbacks(owner)
        }
        return getComponentOrCreate(owner.getComponentKey(), owner) as T
    }

    inline fun <reified T> findComponent(): T =
        findComponentByPredicate { it is T } as T

    fun findComponentByPredicate(predicate: (Any) -> Boolean) =
        componentManager.findComponent(predicate)

    private fun getComponentOrCreate(key: String, owner: IHasComponent): Any {
        return when {
            componentManager.isExist(key) -> componentManager.get(key)
            else -> owner.createComponent().also { componentManager.add(key, it) }
        }
    }

    private fun addActivityCallbacks(app: Application) {
        app.registerActivityLifecycleCallbacks(ActivityLifecycleHelper(componentManager))
    }
}