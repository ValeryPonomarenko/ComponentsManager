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
        @JvmStatic val instance = InjectionManager()
    }

    private val componentsStore = ComponentsStore()

    @Suppress("UNCHECKED_CAST")
    fun <T> bindComponent(owner: IHasComponent): T {
        when (owner) {
            is Application -> addLifecycleCallbacks(owner)
        }
        return getComponentOrCreate(owner.getComponentKey(), owner) as T
    }

    inline fun <reified T> findComponent(): T =
        findComponentByPredicate { it is T } as T

    fun findComponentByPredicate(predicate: (Any) -> Boolean) =
        componentsStore.findComponent(predicate)

    private fun getComponentOrCreate(key: String, owner: IHasComponent): Any {
        return when {
            componentsStore.isExist(key) -> componentsStore.get(key)
            else -> owner.createComponent().also { componentsStore.add(key, it) }
        }
    }

    private fun addLifecycleCallbacks(app: Application) {
        app.registerActivityLifecycleCallbacks(ActivityLifecycleHelper(componentsStore))
    }
}