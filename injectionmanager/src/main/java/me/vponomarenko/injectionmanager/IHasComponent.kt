package me.vponomarenko.injectionmanager

/**
 * Author: Valery Ponomarenko
 * Date: 25/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

interface IHasComponent {
    fun createComponent(): Any
    fun getComponentKey(): String = javaClass.toString()
}