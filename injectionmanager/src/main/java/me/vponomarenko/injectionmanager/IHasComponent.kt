package me.vponomarenko.injectionmanager

/**
 * Author: Valery Ponomarenko
 * Date: 25/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

interface IHasComponent<out T> {
    /**
     * Returns the component that will be saved in the store.
     */
    fun getComponent(): T

    /**
     * Return the key, this key identifies the component in the store.
     * The key must be unique for every component.
     */
    fun getComponentKey(): String = javaClass.toString()
}