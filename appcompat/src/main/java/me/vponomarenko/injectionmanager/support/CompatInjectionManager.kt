package me.vponomarenko.injectionmanager.support

import android.app.Application
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.InjectionManager

/**
 * Author: Valery Ponomarenko
 * Date: 25/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

object CompatInjectionManager {
    @JvmStatic
    val instance = InjectionManager(CompatLifecycleListener())

    @JvmStatic
    fun init(app: Application) = instance.init(app)

    @JvmStatic
    fun <T> bindComponent(owner: IHasComponent<T>): T = instance.bindComponent(owner)

    @JvmStatic
    fun <T> bindComponentToCustomLifecycle(owner: IHasComponent<T>) = instance.bindComponentToCustomLifecycle<T>(owner)

    @JvmStatic
    inline fun <reified T> findComponent(): T = instance.findComponent()

    @JvmStatic
    fun findComponent(predicate: (Any) -> Boolean) = instance.findComponent(predicate)
}