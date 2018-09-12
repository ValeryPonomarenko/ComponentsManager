package me.vponomarenko.feature.b.di

import dagger.Component
import me.vponomarenko.core.di.AppDependency
import me.vponomarenko.feature.b.SecondFragment

/**
 * Author: Valery Ponomarenko
 * Date: 25/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@SecondFeatureScope
@Component(modules = [SecondFeatureModule::class], dependencies = [AppDependency::class])
interface SecondFeatureComponent {
    fun inject(fragment: SecondFragment)
}