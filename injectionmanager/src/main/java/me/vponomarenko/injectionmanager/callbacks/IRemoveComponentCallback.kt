package me.vponomarenko.injectionmanager.callbacks

/**
 * Author: Valery Ponomarenko
 * Date: 25/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

interface IRemoveComponentCallback {
    /**
     * This method notifies the store to destroy the component with the given [key].
     */
    fun onRemove(key: String)
}