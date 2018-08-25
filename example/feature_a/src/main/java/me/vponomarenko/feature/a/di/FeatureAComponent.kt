package me.vponomarenko.feature.a.di

import dagger.Component
import me.vponomarenko.core.di.AppDependencies
import me.vponomarenko.feature.a.FragmentA
import me.vponomarenko.injectionmanager.x.XInjectionManager

/**
 * Author: Valery Ponomarenko
 * Date: 25/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@FeatureAScope
@Component(dependencies = [AppDependencies::class])
interface FeatureAComponent {
    fun inject(fragment: FragmentA)

    class Initializer private constructor() {
        companion object {
            fun init(): FeatureAComponent =
                DaggerFeatureAComponent.builder()
                    .appDependencies(XInjectionManager.instance.findComponent())
                    .build()
        }
    }
}