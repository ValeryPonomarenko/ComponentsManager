package me.vponomarenko.feature.b.di

import dagger.Module
import dagger.Provides
import me.vponomarenko.core.TextHolder
import me.vponomarenko.feature.b.SomeClass

/**
 * Author: Valery Ponomarenko
 * Date: 25/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Module
class SecondFeatureModule {
    @SecondFeatureScope
    @Provides
    fun provideSomeClass() = SomeClass()
}