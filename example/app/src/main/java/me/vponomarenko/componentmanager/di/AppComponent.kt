package me.vponomarenko.componentmanager.di

import dagger.Component
import me.vponomarenko.componentmanager.App
import me.vponomarenko.core.di.AppDependencies
import javax.inject.Singleton

/**
 * Author: Valery Ponomarenko
 * Date: 25/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : AppDependencies {
    fun inject(app: App)

    class Initializer private constructor() {
        companion object {
            fun init(): AppComponent = DaggerAppComponent.builder().build()
        }
    }
}