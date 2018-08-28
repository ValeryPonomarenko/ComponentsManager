package me.vponomarenko.injectionmanager.customlifecycle

/**
 * Author: Valery Ponomarenko
 * Date: 28/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

interface IComponentLifecycle {
    /**
     * Removes the component from the store
     */
    fun remove()
}