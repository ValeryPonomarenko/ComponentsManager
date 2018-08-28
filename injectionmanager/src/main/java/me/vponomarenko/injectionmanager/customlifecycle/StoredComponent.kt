package me.vponomarenko.injectionmanager.customlifecycle

/**
 * Author: Valery Ponomarenko
 * Date: 28/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

data class StoredComponent<T>(
    val component: T,
    val lifecycle: IComponentLifecycle
)