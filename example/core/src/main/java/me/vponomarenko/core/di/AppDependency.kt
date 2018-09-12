package me.vponomarenko.core.di

import me.vponomarenko.core.TextHolder

/**
 * Author: Valery Ponomarenko
 * Date: 25/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

interface AppDependency {
    fun provideSingletonTextHolder(): TextHolder
}