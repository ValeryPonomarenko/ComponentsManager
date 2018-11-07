package me.vponomarenko.injectionmanager

import android.app.Application
import me.vponomarenko.injectionmanager.callbacks.ILifecycleListener
import me.vponomarenko.injectionmanager.customlifecycle.StoredComponent

/**
 * Author: Valery Ponomarenko
 * Date: 25/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class InjectionManager(lifecycleListener: ILifecycleListener) {

    private val componentsStore = ComponentsStore()

    private val componentsController: ComponentsController

    init {
        componentsController =
            ComponentsController(componentsStore, lifecycleListener)
    }

    /**
     * Adds the lifecycle callbacks listeners
     */
    fun init(app: Application) {
        componentsController.addLifecycleCallbackListeners(app)
    }

    /**
     * Returns the created or saved component and binds it the [owner]'s lifecycle, so
     * when the [owner] is destroyed, the component will be destroyed too.
     */
    @Suppress("UNCHECKED_CAST")
    fun <T> bindComponent(owner: IHasComponent): T = componentsController.bindComponent(owner) as T

    /**
     * Returns the created or saved component and binds it to the custom lifecycle.
     * The difference between this method and the bindComponent method is that when the [owner] is
     * destroyed, the component will not be destroyed. It is up to you to destroy the component.
     */
    fun <T> bindComponentToCustomLifecycle(owner: IHasComponent) =
        StoredComponent<T>(
            bindComponent(owner),
            componentsController.getCustomLifecycleForKey(owner.getComponentKey())
        )

    /**
     * Finds the component by the given class
     *
     * @throws me.vponomarenko.injectionmanager.exeptions.ComponentNotFoundException
     */
    inline fun <reified T> findComponent(): T = findComponent { it is T } as T

    /**
     * Finds the component by [predicate]
     *
     * @throws me.vponomarenko.injectionmanager.exeptions.ComponentNotFoundException
     */
    fun findComponent(predicate: (Any) -> Boolean) = componentsStore.findComponent(predicate)
}