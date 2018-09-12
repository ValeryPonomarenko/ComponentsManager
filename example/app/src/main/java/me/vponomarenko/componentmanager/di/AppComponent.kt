package me.vponomarenko.componentmanager.di

import dagger.Component
import me.vponomarenko.componentmanager.SimpleApplication
import me.vponomarenko.core.di.AppDependency
import javax.inject.Singleton

/**
 * Author: Valery Ponomarenko
 * Date: 25/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : AppDependency {
    fun inject(simpleApplication: SimpleApplication)

    class Initializer private constructor() {
        companion object {
            fun init(): AppComponent = DaggerAppComponent.builder().build()
        }
    }
}