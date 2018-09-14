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

    fun init(app: Application) {
        app.registerActivityLifecycleCallbacks(ActivityLifecycleHelper(componentsStore))
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> bindComponent(owner: IHasComponent): T =
        getComponentOrCreate(owner.getComponentKey(), owner) as T

    inline fun <reified T> findComponent(): T =
        findComponent { it is T } as T

    fun findComponent(predicate: (Any) -> Boolean): Any =
        componentsStore.findComponent(predicate)

    private fun getComponentOrCreate(key: String, owner: IHasComponent): Any {
        return when {
            componentsStore.isExist(key) -> componentsStore.get(key)
            else -> owner.getComponent().also { componentsStore.add(key, it) }
        }
    }
}