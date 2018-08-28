package me.vponomarenko.feature.c.di

import dagger.Component
import me.vponomarenko.core.di.AppDependencies
import me.vponomarenko.feature.c.FragmentC
import me.vponomarenko.injectionmanager.x.XInjectionManager

/**
 * Author: Valery Ponomarenko
 * Date: 28/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@FeatureCScope
@Component(dependencies = [AppDependencies::class], modules = [FeatureCModule::class])
interface FeatureCComponent {
    fun inject(fragment: FragmentC)

    class Initializer private constructor() {
        companion object {
            fun init(): FeatureCComponent =
                DaggerFeatureCComponent.builder()
                    .appDependencies(XInjectionManager.instance.findComponent())
                    .build()
        }
    }
}