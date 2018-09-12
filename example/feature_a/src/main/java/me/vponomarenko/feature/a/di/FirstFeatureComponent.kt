package me.vponomarenko.feature.a.di

import dagger.Component
import me.vponomarenko.core.di.AppDependency
import me.vponomarenko.feature.a.FirstFragment

/**
 * Author: Valery Ponomarenko
 * Date: 25/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@FirstFeatureScope
@Component(dependencies = [AppDependency::class])
interface FirstFeatureComponent {
    fun inject(fragment: FirstFragment)
}