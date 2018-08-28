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

    val keysForCustomLifecycle = mutableListOf<String>()

    override fun onRemove(key: String) {
        if (keysForCustomLifecycle.contains(key)) return
        componentsStore.remove(key)
    }

    fun bindComponent(owner: IHasComponent): Any {
        if (owner is Application) {
            addApplicationLifecycleListener(owner)
        }
        return buildOrCreateComponent(owner)
    }

    fun getCustomLifecycleForKey(key: String): IComponentLifecycle {
        keysForCustomLifecycle.add(key)
        return object : IComponentLifecycle {
            override fun remove() {
                keysForCustomLifecycle.remove(key)
                onRemove(key)
            }
        }
    }

    private fun addApplicationLifecycleListener(app: Application) {
        platformLifecycleCallbacks.addLifecycleListener(app, this)
    }

    private fun buildOrCreateComponent(owner: IHasComponent): Any {
        with (owner.getComponentKey()) {
            if (componentsStore.isExist(this)) {
                return componentsStore.get(this)
            }
            return owner.createComponent().also { componentsStore.add(this, it) }
        }
    }
}