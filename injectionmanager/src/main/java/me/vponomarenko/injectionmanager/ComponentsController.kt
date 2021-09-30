package me.vponomarenko.injectionmanager

import android.app.Application
import me.vponomarenko.injectionmanager.callbacks.ILifecycleListener
import me.vponomarenko.injectionmanager.callbacks.IRemoveComponentCallback
import me.vponomarenko.injectionmanager.customlifecycle.IComponentLifecycle

/**
 * Author: Valery Ponomarenko
 * Date: 28/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

internal class ComponentsController(
    private val componentsStore: ComponentsStore,
    private val platformLifecycleCallbacks: ILifecycleListener
) : IRemoveComponentCallback {

    private val keysForCustomLifecycle = mutableSetOf<String>()

    override fun onRemove(key: String) {
        if (keysForCustomLifecycle.contains(key)) return
        componentsStore.remove(key)
    }

    fun addLifecycleCallbackListeners(app: Application) {
        platformLifecycleCallbacks.addLifecycleListener(app, this)
    }

    fun <T> bindComponent(owner: IHasComponent<T>) = buildOrCreateComponent(owner)

    fun getCustomLifecycleForKey(key: String): IComponentLifecycle {
        keysForCustomLifecycle.add(key)
        return object : IComponentLifecycle {
            override fun destroy() {
                keysForCustomLifecycle.remove(key)
                onRemove(key)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> buildOrCreateComponent(owner: IHasComponent<T>): T {
        with(owner.getComponentKey()) {
            if (componentsStore.isExist(this)) {
                return componentsStore.get(this) as T
            }
            return owner.getComponent().also { componentsStore.add(this, it as Any) }
        }
    }
}