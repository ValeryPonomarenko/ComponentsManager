package me.vponomarenko.injectionmanager.exeptions

/**
 * Author: Valery Ponomarenko
 * Date: 14/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Deprecated(
    "The class has wrong package, so use me.vponomarenko.injectionmanager.exceptions.ComponentNotFoundException instead. " +
            "Will be removed in 3.0.0"
)
open class ComponentNotFoundException(key: String) :
    Throwable("Component of the $key type was not found")