package me.vponomarenko.injectionmanager

import me.vponomarenko.injectionmanager.exeptions.ComponentNotFoundException

/**
 * Author: Valery Ponomarenko
 * Date: 25/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

internal class ComponentsStore {
    private val componentsForView = mutableMapOf<String, Any>()

    fun isExist(key: String) = componentsForView.containsKey(key)

    fun add(key: String, component: Any) {
        componentsForView[key] = component
    }

    fun get(key: String): Any =
        componentsForView[key] ?: throw ComponentNotFoundException()

    fun remove(key: String) {
        componentsForView.remove(key)
    }

    fun findComponent(predicate: (Any) -> Boolean): Any {
        for ((_, component) in componentsForView) {
            if (predicate(component)) { return component }
        }
        throw ComponentNotFoundException()
    }
}