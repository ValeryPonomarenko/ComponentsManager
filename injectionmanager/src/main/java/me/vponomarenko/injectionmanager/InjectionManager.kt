package me.vponomarenko.injectionmanager

import android.app.Application
import me.vponomarenko.injectionmanager.callbacks.ILifecycleListener
import me.vponomarenko.injectionmanager.customlifecycle.StoredComponent
import me.vponomarenko.injectionmanager.exceptions.ComponentNotFoundException

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
    fun <T> bindComponent(owner: IHasComponent<T>): T = componentsController.bindComponent(owner)

    /**
     * Returns the created or saved component and binds it to the custom lifecycle.
     * The difference between this method and the bindComponent method is that when the [owner] is
     * destroyed, the component will not be destroyed. It is up to you to destroy the component.
     */
    fun <T> bindComponentToCustomLifecycle(owner: IHasComponent<T>) =
        StoredComponent<T>(
            bindComponent(owner),
            componentsController.getCustomLifecycleForKey(owner.getComponentKey())
        )

    /**
     * Finds the component by the given class
     *
     * @throws me.vponomarenko.injectionmanager.exceptions.ComponentNotFoundException
     */
    inline fun <reified T> findComponent(): T {
        val predicate = object : (Any) -> Boolean {
            override fun invoke(component: Any): Boolean = component is T

            override fun toString(): String = T::class.java.simpleName
        }
        return findComponent(predicate) as T
    }

    /**
     * Finds the component by the given class.
     * Returns null if component was not found.
     */
    inline fun <reified T> findComponentOrNull(): T? {
        val predicate = object : (Any) -> Boolean {
            override fun invoke(component: Any): Boolean = component is T

            override fun toString(): String = T::class.java.simpleName
        }
        return findComponentOrNull(predicate) as? T
    }

    /**
     * Finds the component by [predicate]
     *
     * @throws me.vponomarenko.injectionmanager.exceptions.ComponentNotFoundException
     */
    fun findComponent(predicate: (Any) -> Boolean): Any =
        componentsStore.findComponent(predicate)
            ?: throw ComponentNotFoundException(predicate.toString())

    /**
     * Finds the component by [predicate].
     * Returns null if component was not found.
     */
    fun findComponentOrNull(predicate: (Any) -> Boolean): Any? =
        componentsStore.findComponent(predicate)
}