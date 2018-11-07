package me.vponomarenko.feature.b.di

import dagger.Component
import me.vponomarenko.core.di.AppDependencies
import me.vponomarenko.feature.b.FragmentB
import me.vponomarenko.feature.b.FragmentChildB
import me.vponomarenko.injectionmanager.x.XInjectionManager

/**
 * Author: Valery Ponomarenko
 * Date: 25/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@FeatureBScope
@Component(modules = [FeatureBModule::class], dependencies = [AppDependencies::class])
interface FeatureBComponent {
    fun inject(fragmentB: FragmentB)
    fun inject(fragmentChildB: FragmentChildB)

    class Initializer private constructor() {
        companion object {
            fun init(): FeatureBComponent =
                DaggerFeatureBComponent.builder()
                    .appDependencies(XInjectionManager.findComponent())
                    .build()
        }
    }
}