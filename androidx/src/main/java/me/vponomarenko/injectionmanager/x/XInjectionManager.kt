package me.vponomarenko.injectionmanager.x

import android.app.Application
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.InjectionManager

/**
 * Author: Valery Ponomarenko
 * Date: 25/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

object XInjectionManager {
    @JvmStatic
    val instance = InjectionManager(XLifecycleListener())

    @JvmStatic
    fun init(app: Application) = instance.init(app)

    @JvmStatic
    fun <T> bindComponent(owner: IHasComponent<T>): T =
        instance.bindComponent(owner)

    @JvmStatic
    fun <T> bindComponentToCustomLifecycle(owner: IHasComponent<T>) =
        instance.bindComponentToCustomLifecycle<T>(owner)

    @JvmStatic
    inline fun <reified T> findComponent(): T =
        instance.findComponent()

    @JvmStatic
    inline fun <reified T> findComponentOrNull(): T? =
        instance.findComponentOrNull()

    @JvmStatic
    fun findComponent(predicate: (Any) -> Boolean): Any =
        instance.findComponent(predicate)

    @JvmStatic
    fun findComponentOrNull(predicate: (Any) -> Boolean): Any? =
        instance.findComponentOrNull(predicate)
}